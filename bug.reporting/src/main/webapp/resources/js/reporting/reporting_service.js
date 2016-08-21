'use strict';

application.factory('Bug', function ($resource) {
	var Bug = $resource('/bug.reporting/rest/reporting/bugs/:bugId', {bugId: '@bugId'},
	{update: {method: 'PUT'},
	 query: {method: 'GET', isArray: false }});

	 return Bug;
})

application.factory('Author', function ($resource) {
	var Author = $resource('/bug.reporting/rest/reporting/authors/:authorId', {bugId: '@authorId'},
	{update: {method: 'PUT'},
	 query: {method: 'GET', isArray: false }});

	 return Author;
})

application.factory('Tag', function ($resource) {
  	var Tag = $resource('/bug.reporting/rest/reporting/tags/:tagId', {bugId: '@tagId'},
  	{update: {method: 'PUT'},
  	 query: {method: 'GET', isArray: false }});

  	 return Tag;
  })
;