function LoginController($scope, socket, $uibModal) {
    var ctrl = this;
    ctrl.showModal = false;
    ctrl.username = ctrl.username || '';
    ctrl.submitUsername = function() {
        console.log('Log in requested...');
        if (badUsername()) {
            console.log('Log in declined....');
            $uibModal.open({templateUrl: 'js/modalContent.html'});
        }
        else {
            socket.emit('loginRequest', {id: ctrl.username});
            console.log('Log in accepted');
        }

    }


    function badUsername() {
        console.log('ctrl.username ' + ctrl.username);
        if (ctrl.username === '') {
            return true;
        }
        else {
            return false;
        }
    }
    ctrl.ok = function() {
        console.log('Closed modal');
    };
};

angular.module('login', ['socketService', 'ui.bootstrap']).component('login', {
    templateUrl: 'js/login.html',
    controller: LoginController,
    bindings: {
        username: '<'
    }
});

