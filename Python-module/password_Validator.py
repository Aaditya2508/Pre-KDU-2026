username = str(input("Enter username: "))
confirm_username = str(input("Confirm Username: ")) 

print("Length 1:",len(username))
print("Length 1:",len(confirm_username))
print("Length match: ",len(confirm_username) == len(username))
print("Strings match: ",confirm_username == username)