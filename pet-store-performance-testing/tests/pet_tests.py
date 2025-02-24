import random




class PetEndpointTests():
    def __init__(self, client):
        self.client = client
        self.pet_ids = []

   
    def get_pets_by_status(self):
        self.client.get("/pet/findByStatus?status=available")

   
    def create_pet(self):
        pet_id = random.randint(1000, 9999) # Ensure the ID matches what you query
        pet_data = {
            "id": pet_id,
            "category": {"id": 1, "name": "Dog"},
            "name": f"TestPet{pet_id}",
            "photoUrls": ["https://example.com/photo.jpg"],
            "tags": [{"id": 1, "name": "test"}],
            "status": "available"
        }
        response = self.client.post("/pet", json=pet_data)
        if response.status_code == 200:
            self.pet_ids.append(pet_id)
            print(f"Created pet with ID {pet_id}")
        else:
            print(f"Failed to create pet: {response.status_code}")

   
    def get_pet_by_id(self):
        if not self.pet_ids:
            return
        elif self.pet_ids:
            pet_id = random.choice(self.pet_ids)
            self.client.get(f"/pet/{pet_id}")


if __name__ == "__main__":
    import os
    os.system("locust -f pet_tests.py")