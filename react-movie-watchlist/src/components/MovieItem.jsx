function MovieItem({ movie, onDelete, onToggleWatched }) {
  return (
    <li className={movie.watched ? "watched" : ""}>
      <span>
        {movie.name} ({movie.rating} Stars)
      </span>

      <div>
        <button onClick={() => onToggleWatched(movie.id)}>
          {movie.watched ? "Unwatch" : "Watched"}
        </button>

        <button onClick={() => onDelete(movie.id)}>Delete</button>
      </div>
    </li>
  );
}

export default MovieItem;
