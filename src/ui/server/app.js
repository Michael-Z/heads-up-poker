var express = require('express');
var app = express();
var server = require('http').Server(app)
var io = require('socket.io')(server);
var PokerEvaluator = require("poker-evaluator");

var obj = PokerEvaluator.evalHand(["Th", "Kh", "Qh", "Jh", "9h", "3s", "5h"]);

console.log(obj);
var net = require('net');

var PORT = 3500;
var HOST = 'localhost';

app.use(express.static(__dirname + '/src'));


app.get('/', function (req, res) {
    var options = {
        root: __dirname + '/src/',
        dotfiles: 'deny',
        headers: {
            'x-timestamp': Date.now(),
            'x-sent': true
        }
    };
    var fileName = "test.html";
    res.sendFile(fileName, options, function(err) {
        if (err) {
          next(err);
        } else {
          console.log('Sent:', fileName);
        };
    });
});

io.on('connection', function (socket) {
    socket.emit('news', { hello: 'world' });
    socket.on('msg', function (data) { //testing socket communication
        console.log(data);
    });

    socket.on('testmessage', function (data) {
        console.log(data);
    });

    socket.on('action', function (data) {
        if (data.action === 'bet') {
            socket.emit('cfr', {action: 'call', amount: data.amount}); //call/raise/fold on a bet
        }
        if (data.action === 'check') {
            socket.emit('fcb', { action: 'bet', amount: 0 }); //call/bet/fold on a check
        }
        if (data.action === 'raise') {
            socket.emit('cfr', { action: 'call', amount: 0 }); //call/bet/fold on a check
        }
    });
    socket.on('call', function (data) {
        socket.emit('fcb', {action: 'check', amount: 0});
    });
    socket.on('newaction', function (data) {
        if (data.action === 'check') {
            socket.emit('fcb', {action: 'check', amount: data.amount});
        }
    });

    socket.on('startgame', function (data) {
        //receive the card info.
        socket.emit('cfr', {action: 'call', amount: 0}); //start game will be call raise or fold on the - big blind
    });

    socket.on('evaluate hands', function (data) {
        var playerHandRank = PokerEvaluator.evalHand(data.communityCards.concat(data.playerCards));
        var aiHandRank = PokerEvaluator.evalHand(data.communityCards.concat(data.aiCards));
        console.log('community: ', data.communityCards.concat(data.aiCards));
        console.log('community: ', data.playerCards);
        console.log('community: ', data.aiCards);
        console.log(aiHandRank);
        console.log(playerHandRank);
        var returnData = {};
        if (playerHandRank.handType > aiHandRank.handType ||
           (playerHandRank.handType === aiHandRank.handType
            && playerHandRank.handRank > aiHandRank.handRank)) {
            console.log('Win for the Player'); //inform AI about this.
            socket.emit('playerwin', playerHandRank);
        }
        else if (playerHandRank.handType < aiHandRank.handType ||
                (playerHandRank.handType === aiHandRank.handType
             && playerHandRank.handRank < aiHandRank.handRank)) {
            console.log('Win for the AI!!!!');
            socket.emit('aiwin', aiHandRank);

        } else { //its a draw
            console.log('Its A draww. What are the odds?');
            socket.emit('playeraidraw', playerHandRank);
        }
    });
});


server.listen(3000)
