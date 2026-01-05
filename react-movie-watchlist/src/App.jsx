import { useState } from "react";
import AddMovie from "./components/AddMovie";
import MovieList from "./components/MovieList";
import SearchBar from "./components/SearchBar";

function App() {
  const [movies, setMovies] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  const addMovie = (movie) => {
    setMovies((prev) => [...prev, movie]);
  };

  const deleteMovie = (id) => {
    setMovies((prev) => prev.filter((movie) => movie.id !== id));
  };

  const toggleWatched = (id) => {
    setMovies((prev) =>
      prev.map((movie) =>
        movie.id === id ? { ...movie, watched: !movie.watched } : movie
      )
    );
  };

  const clearAllMovies = () => {
    setMovies([]);
  };

  const filteredMovies = movies.filter((movie) =>
    movie.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="container">
      <h1>Movie Watchlist</h1>

      <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      <AddMovie onAddMovie={addMovie} />

      <p>Movies in Watchlist: {movies.length}</p>

      {movies.length === 0 ? (
        <p>Your watchlist is empty. Add your first movie!</p>
      ) : filteredMovies.length === 0 ? (
        <p>No movies found. Try a different search!</p>
      ) : (
        <MovieList
          movies={filteredMovies}
          onDelete={deleteMovie}
          onToggleWatched={toggleWatched}
        />
      )}

      {movies.length > 0 && (
        <button onClick={clearAllMovies} className="clear-btn">
          Clear All
        </button>
      )}
    </div>
  );
}

export default App;
