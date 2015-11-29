'use strict';

var chai = require('chai');
var expect = chai.expect;
var assert = chai.assert;
var should = chai.should();

var steps = function() {

    var page = require('./HomePage.js');

    this.Given(/^Ich bin auf der Homepage$/, function() {
        return page.navigateAndCallWithUrl(function(url) {
            return assert.equal(url, 'http://localhost:8080/pages/books.html',
                    'URL stimmt nicht überein');
        });
    });

    this.Then(/^muss es eine Überschrift geben benannt: "([^"]*)"$/, function(
            heading) {

        return page.welcome.getText().then(function(text) {
            console.log("TEXT" + text);
            return assert.equal(text, heading, "TEXT stimmt nicht");
        });
    });

    this.Then(/^ich sollte einen "([^"]*)" link sehen$/, function(link, callback) {
        callback.pending();
    });

    this.Then(/^ich sollte keinen "([^"]*)" link sehen$/, function(link, callback) {
        callback.pending();
        // support.isElementPresent(this, link, function(result){
        // result.should.equal(false);
        // setTimeout(callback, 1000);
        // });
    });

};

module.exports = steps;
