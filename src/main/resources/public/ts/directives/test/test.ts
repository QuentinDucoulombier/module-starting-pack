import {ng} from 'entcore';
import {RootsConst} from "../../core/constants/roots.const";

interface IViewModel {
    $onInit(): any;

    $onDestroy(): any;
}

export const test = ng.directive('sessionToProgressionForm', function () {
    return {
        scope: {
            session: '=',
            homeworks: '='
        },
        restrict: 'E',
        templateUrl: `${RootsConst.directive}test/test.html`,
        controllerAs: 'vm',
        bindToController: true,
        replace: false,
        controller: function () {
            const vm: IViewModel = <IViewModel>this;

            vm.$onInit = async () => {

            };
        },

        link: function ($scope) {
            const vm: IViewModel = $scope.vm;

            vm.$onDestroy = async () => {
            };
        }
    };
});