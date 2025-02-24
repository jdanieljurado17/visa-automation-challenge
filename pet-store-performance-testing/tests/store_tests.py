import random
from datetime import datetime, timedelta, timezone

class StoreEndpointTests():
    def __init__(self, client):
        self.client = client
        self.order_ids = []

   
    def get_orders_inventory(self):
        self.client.get("/store/inventory")

   
    def create_order(self):
        order_id = random.randint(1000, 9999) # Ensure the ID matches what you query
        ship_date = (datetime.now(timezone.utc) + timedelta(hours=random.randint(1, 72))).isoformat()
        order_data = {
                "id": order_id,
                "petId":  random.randint(100, 999),
                "quantity": random.randint(1, 5),
                "shipDate": ship_date,
                "status": "approved",
                "complete": True
            }
        response = self.client.post("/store/order", json=order_data)
        if response.status_code == 200:
            self.order_ids.append(order_id)
            print(f"Created order with ID {order_id}")
        else:
            print(f"Failed to create pet: {response.status_code}")

   
    def get_order_by_id(self):
        if not self.order_ids:
            return
        elif self.order_ids:
            order_id = random.choice(self.order_ids)
            self.client.get(f"/store/order/{order_id}")


if __name__ == "__main__":
    import os
    os.system("locust -f store_tests.py")