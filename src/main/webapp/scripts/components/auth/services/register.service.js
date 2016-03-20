'use strict';

angular.module('jrdbaseApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


