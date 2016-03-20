 'use strict';

angular.module('jrdbaseApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-jrdbaseApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-jrdbaseApp-params')});
                }
                return response;
            }
        };
    });
