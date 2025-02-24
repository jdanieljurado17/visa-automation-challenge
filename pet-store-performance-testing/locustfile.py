from locust import HttpUser, task, between
from tests.pet_tests import PetEndpointTests
from tests.store_tests import StoreEndpointTests

import config 

class PetStoreUser(HttpUser):
    host = config.HOST
    wait_time = between(config.WAIT_TIME_MIN, config.WAIT_TIME_MAX)

    def on_start(self):
        """Initialize tests with self.client"""
        self.pet_load_tests = PetEndpointTests(self.client)
        self.store_tests = StoreEndpointTests(self.client)
    
    @task(2)
    def create_pet(self):
        self.pet_load_tests.create_pet()

    @task(2)
    def create_order(self):
        self.store_tests.create_order()
    
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

if __name__ == "__main__":
    import os
    os.system(f"locust -f locustfile.py --users {config.USERS} --spawn-rate {config.SPAWN_RATE} --run-time {config.RUN_TIME}")
