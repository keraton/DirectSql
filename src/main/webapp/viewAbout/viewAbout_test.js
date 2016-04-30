'use strict';

describe('myApp.viewAbout module', function() {

  beforeEach(module('myApp.view2'));

  describe('viewAbout controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var view2Ctrl = $controller('AboutCtrl');
      expect(view2Ctrl).toBeDefined();
    }));

  });
});