import MovieItem from "./MovieItem";

function MovieList({ movies, onDelete, onToggleWatched }) {
  return (
    <ul className="movie-list">
      {movies.map((movie) => (
        <MovieItem
          key={movie.id}
          movie={movie}
          onDelete={onDelete}
          onToggleWatched={onToggleWatched}
        />
      ))}
    </ul>
  );
}

export default MovieList;
