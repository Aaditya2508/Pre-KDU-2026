import { useState } from "react";

function AddMovie({ onAddMovie }) {
  const [movieName, setMovieName] = useState("");
  const [rating, setRating] = useState(1);

  const handleAdd = () => {
    if (movieName.trim() === "") {
      alert("Movie name cannot be empty.");
      return;
    }

    onAddMovie({
      id: Date.now(),
      name: movieName.trim(),
      rating,
      watched: false
    });

    setMovieName("");
    setRating(1);
  };

  return (
    <div className="add-movie">
      <input
        type="text"
        placeholder="Enter movie name"
        value={movieName}
        onChange={(e) => setMovieName(e.target.value)}
      />

      <select value={rating} onChange={(e) => setRating(Number(e.target.value))}>
        {[1, 2, 3, 4, 5].map((r) => (
          <option key={r} value={r}>
            {r} Star{r > 1 ? "s" : ""}
          </option>
        ))}
      </select>

      <button onClick={handleAdd}>Add to Watchlist</button>
    </div>
  );
}

export default AddMovie;
