(function () {

    'use strict';

    /* @ngInject */
    function footerdir() {

        function main(scope, element, attrs) {

        }

        var directive = {
            bindToController: false,
            templateUrl: 'app/shared/footer/footer.html',
            link: main,
            restrict: 'A',
            scope: {}
        };
        return directive;

    }

    footerdir.$inject = [];

    /*jslint white:true */
    /*global angular */
    angular
        .module('BooksApp')
        .directive('footer', footerdir);

}());
