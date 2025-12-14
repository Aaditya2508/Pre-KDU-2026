# Open and read the CSV file
with open("C:\\Users\\HP\\Desktop\\Pre-KDU-2026\\Python-module\\watchlist.csv", "r") as file:
    line = file.read()

# Split movie titles and strip whitespace
movies = [movie.strip() for movie in line.split(",")]

# Dictionary to count movie frequency
movie_count = {}

for movie in movies:
    movie_count[movie] = movie_count.get(movie, 0) + 1

# Sort movies by watch count (descending)
top_movies = sorted(movie_count.items(), key=lambda x: x[1], reverse=True)

# Print top 3 most watched movies
print("Top 3 Most Watched Movies:")
for movie, count in top_movies[:3]:
    print(f"{movie}: {count}")
