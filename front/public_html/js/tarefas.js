angular
        .module('tarefasApp', [])
        .controller('TarefasController', function ($scope, TarefasService) {

            listar();

            function listar() {
                TarefasService.listar().then(function (resposta) {
                    $scope.tarefas = resposta.data;
                });
            }
            
            function erro() {
                alert('Erro!');
            }

            $scope.salvar = function (tarefa) {
                if (tarefa.id) {
                    TarefasService.atualizar(tarefa).then(listar, erro);
                } else {
                    TarefasService.adicionar(tarefa).then(listar, erro);
                }
                $scope.tarefa = "";
            };

            $scope.concluir = function (tarefa) {
                TarefasService.concluir(tarefa).then(listar, erro);
            };

            $scope.excluir = function (tarefa) {
                TarefasService.excluir(tarefa).then(listar, erro);
            };

            $scope.editar = function (tarefa) {
                $scope.tarefa = angular.copy(tarefa);
            };
            
            $scope.buscar = function(filtro) {
                TarefasService.buscar(filtro).then(function(resposta) {
                    $scope.tarefas = resposta.data;
                }, erro);
            };

        })
        .service('TarefasService', function ($http) {

            var api = 'http://localhost:8000/tarefas/webresources/tarefas/';

            this.adicionar = function (tarefa) {
                return $http.post(api, tarefa);
            };
            
            this.atualizar = function(tarefa) {
                return $http.put(api + tarefa.id, tarefa);
            };

            this.concluir = function (tarefa) {
                return $http.post(api + tarefa.id + '/concluir', tarefa);
            };

            this.listar = function () {
                return $http.get(api);
            };

            this.excluir = function (tarefa) {
                return $http.delete(api + tarefa.id);
            };
            
            this.buscar = function(filtro) {
                return $http.get(api + 'busca', {
                    params: {
                        descricao: filtro
                    }
                });
            };

        });