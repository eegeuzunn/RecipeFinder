# RECIPE FINDER
This is a Recipe Finder API that I developed to learn and practice the Spring Framework.

The following endpoints allow you to retrieve, create, and delete recipes. Pagination and filtering are also implemented.

- /api/v1/recipes           @GET fecths all recipes

- /api/v1/page/recipes      @GET fetchs recipes according to size and page parameters

- /api/v1/filter/recipes    @GET fetchs recipes withn filtering name, ingredients and category

- /api/v1/recipes           @POST creates new recipe

- /api/v1/recipes/id        @DELETE deletes recipe
