line = str(input())

genres = [genre.strip() for genre in line.split(",")]

genre_list = []
genre_set = set()
genre_dict = {}

for genre in genres:
    genre_list.append(genre)
    genre_set.add(genre)
    genre_dict[genre] = genre_dict.get(genre, 0) + 1

print("List:", genre_list)
print("Set:", genre_set)
print("Dictionary:", genre_dict)
