from locust import HttpUser, task, between
import config 

class PetStoreUser(HttpUser):
    host = config.HOST
    wait_time = between(1, 3)   # Users wait 1-3 seconds between requests

    @task
    def get_pets_by_status(self):
        self.client.get("/pet/findByStatus?status=available")

if __name__ == "__main__":
    import os
    os.system("locust -f locustfile.py")
