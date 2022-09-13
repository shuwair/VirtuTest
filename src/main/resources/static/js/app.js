(function(){

  var app = angular.module('notesApp',['ngRoute', 'ngMaterial']);

  app.config(['$locationProvider', '$routeProvider',
      function ($locationProvider, $routeProvider) {

        $routeProvider
          .when('/', {
            templateUrl: '/partials/notes-view.html',
            controller: 'notesController'
          })
          .when('/login', {
             templateUrl: '/partials/login.html',
             controller: 'loginController',
          })
          .otherwise('/');
      }
  ]);

  app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
      $rootScope.$on('$routeChangeStart', function (event) {

          if ($location.path() == "/login"){
             return;
          }

          if (!AuthService.isLoggedIn()) {
              console.log('DENY');
              event.preventDefault();
              $location.path('/login');
          }
      });
  }]);


  app.service('AuthService', function($http){
        var loggedUser = null;

        function login (username, password){
            return $http.post("api/login", {username: username, password: password}).then(function(user){
                loggedUser = user;
            }, function(error){
                loggedUser = null;
            })
        }

        function isLoggedIn(){
            return loggedUser != null;
        }
        return {
            login : login,
            isLoggedIn: isLoggedIn
        }
  });
  


  app.controller('loginController', function($scope, AuthService, $location,NoteService){

    $scope.invalidCreds = false;
    $scope.login = {
        username : null,
        password : null
    };

    $scope.login = function(){
        AuthService.login($scope.login.username, $scope.login.password).then(function(user){
            console.log(user);
            $location.path("/");
        }, function(error){
            console.log(error);
            $scope.invalidCreds = true;
        });
    };
  });


    app.service('NoteService', function($http){
	
	 var savednote = null; 
	 var savednotes = [];
	 
	  function savenote (noteName, noteText){
            return $http.post("note/save", {noteName: noteName, noteText: noteText}).then(function(note){
                savednote = note;
            },function(error){
                savednote = null;
            })
        }
        
		function getallnotes(){
            return $http.get("https://whispering-woodland-9020.herokuapp.com/getAllBooks").then(function(notes){
                return notes.data;
            },function(error){
                savednotes = [];
            })
        }
        return {
	 		savenote: savenote,
	 		getallnotes: getallnotes
		}

	});

  app.controller('notesController', function($scope,NoteService,$location){

    $scope.isEditCreateView = false;
    
    $scope.note = {
		id: null,
		noteName: null,
		noteText: null,
		createDate: null
    };
    
     //$scope.data = NoteService.getallnotes();

        $scope.saveNote = function(){
        NoteService.savenote($scope.note.noteName, $scope.note.noteText).then(function(note){
			$scope.note = note;
            console.log(note);
            $location.path("/");
        }, function(error){
            console.log(error);
            alert("error");
        });
    };

    $scope.newNoteView = function(){
        $scope.isEditCreateView = true;
    };

    $scope.deleteNote = function (i) {
      var r = confirm("Are you sure you want to delete this note?");
      if (r == true){
        //TODO delete the note
      }
    };

    $scope.viewNote = function(){
        //TODO
    }
  });
  
})();