from locust import HttpUser, task, between, LoadTestShape
from tests.pet_tests import PetEndpointTests
from tests.store_tests import StoreEndpointTests
from tests.user_tests import UserEndpointTests

import config 

class PetStoreUser(HttpUser):
    host = config.HOST
    wait_time = between(config.WAIT_TIME_MIN, config.WAIT_TIME_MAX)

    def on_start(self):
        """Initialize tests with self.client"""
        self.pet_load_tests = PetEndpointTests(self.client)
        self.store_tests = StoreEndpointTests(self.client)
        self.user_tests = UserEndpointTests(self.client)
    
    @task(2)
    def create_pet(self):
        self.pet_load_tests.create_pet()

    @task(2)
    def create_order(self):
        self.store_tests.create_order()

    @task(2)
    def create_user(self):
        self.user_tests.create_user()
    
    @task(5)  # Higher weight for more frequent execution
    def get_pets_by_status(self):
        self.pet_load_tests.get_pets_by_status()
  
    @task(2)
    def get_pet_by_id(self):
        self.pet_load_tests.get_pet_by_id()    
    
    @task(5)
    def get_store_inventory(self):
        self.store_tests.get_orders_inventory()
    
    @task(2)
    def get_orders_by_id(self):
        self.store_tests.get_order_by_id()

    @task(5)
    def get_user_login(self):
        self.user_tests.get_user_login()
    
    @task(2)
    def get_user_by_username(self):
        self.user_tests.get_user_by_username()


class CustomLoadShape(LoadTestShape):
    """
    Defines a custom user load shape:
    - Ramps up users gradually
    - Holds peak load
    - Ramps down at the end
    """

    stages = [
        {"duration": 30, "users": 10, "spawn_rate": 2},  # Ramp-up (10 users in 30s)
        {"duration": 90, "users": 50, "spawn_rate": 5}, # Peak load (50 users for 1.5 min)
        {"duration": 30, "users": 10, "spawn_rate": 2},  # Ramp-down (back to 10 users)
    ]

    def tick(self):
        run_time = self.get_run_time()

        for stage in self.stages:
            if run_time < stage["duration"]:
                return stage["users"], stage["spawn_rate"]
            run_time -= stage["duration"]

        return None  # Stop test when all stages are done


if __name__ == "__main__":
    import os
    os.system(f"locust -f locustfile.py --users {config.USERS} --spawn-rate {config.SPAWN_RATE} --run-time {config.RUN_TIME}")
