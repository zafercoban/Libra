/**
 * Created by zafercoban on 23/08/15.
 */
'use strict';

/* Filters */

var AppFilters = angular.module('bookApp.filters', []);

AppFilters.filter('interpolate', ['version', function (version) {
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);
AppFilters.filter('breakFilter', function () {
    return function (text) {
        if (text !== undefined) return text.replace(/\n/g, '<br />');
    };
});
