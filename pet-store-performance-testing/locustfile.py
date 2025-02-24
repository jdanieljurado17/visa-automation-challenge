from locust import HttpUser, task, between
import config 

class PetStoreUser(HttpUser):
    host = config.HOST
    wait_time = between(config.WAIT_TIME_MIN, config.WAIT_TIME_MAX)

    @task
    def get_pets_by_status(self):
        self.client.get("/pet/findByStatus?status=available")

if __name__ == "__main__":
    import os
    os.system(f"locust -f locustfile.py --users {config.USERS} --spawn-rate {config.SPAWN_RATE} --run-time {config.RUN_TIME}")
