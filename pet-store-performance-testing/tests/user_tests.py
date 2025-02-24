import random

class UserEndpointTests():
    def __init__(self, client):
        self.client = client
        self.usernames_list = []
        self.password = "12345"

   
    def create_user(self):
        user_id = random.randint(1000, 9999)
        username = random.randint(1000, 9999)
        user_data ={
                    "id": user_id,
                    "username": username,
                    "firstName": f"John {user_id}",
                    "lastName": "Rambo",
                    "email": f"john{user_id}@email.com",
                    "password": self.password,
                    "phone": "12345",
                    "userStatus": 1
                }
        response = self.client.post("/user", json=user_data)
        if response.status_code == 200:
            self.usernames_list.append(username)
            print(f"Created user: {username}")
        else:
            print(f"Failed to create user: {response.status_code}")

    def get_user_login(self):
        if not self.usernames_list:
            print("No users available for login, creating one...")
            self.create_user()  # Ensure at least one user exists

        username = random.choice(self.usernames_list)
        response = self.client.get("/user/login", params={"username": username, "password": self.password})

        if response.status_code == 200:
            print(f"User {username} logged in successfully.")
        else:
            print(f"Failed to log in user {username}: {response.status_code}")
    
    
    def get_user_by_username(self):
        if not self.usernames_list:
            return
        elif self.usernames_list:
            username = random.choice(self.usernames_list)
            self.client.get(f"/user/{username}")


if __name__ == "__main__":
    import os
    os.system("locust -f store_tests.py")