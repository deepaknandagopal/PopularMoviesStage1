# PopularMoviesStage1
Popular Movie Stage 1 mobile app for Udacity.

Completed Tasks
  Build a UI layout for multiple Activities.
  Launch these Activities via Intent.
  Fetch data from themovieDB API

Application Flow:
      -> Upon launch, app will present the user with an grid arrangement of movie posters.
      -> It allows the user to change sort order via a setting:
            *By most popular, or by top rated
      -> It allows the user to tap on a movie poster and transition to a another activit with additional information such as:
            *original title
            *movie poster image thumbnail
            *A plot synopsis (called overview in the api)
            *user rating (called vote_average in the api)
            *release date
Note: 
Generate the API key from themoviedb.org and Insert in the MainActivity.Java 
in the variable "API_Key" to fetch the reponse from the web.
