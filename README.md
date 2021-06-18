# package-tracker
Delivery Tracking System

### Documentation
**The API endpoint documentation can be found in this url:**
_https://item-tracker.herokuapp.com/swagger-ui.html#/Item_Tracking_Management_Endpoints_
**OR**
_https://documenter.getpostman.com/view/875645/TzeXj6te_

### Testing
**Download the postman test collection via this link:** _https://www.getpostman.com/collections/3bdfa07912d86ccc8ed8_

1) Test 1: Register new item for pick up
This test will register an item for pick up and store the _trackingId_ as an environment variable to be used in other tests
   
2) Test 2: Current item delivery status
This test returns the delivery status of the item with trackingId set as the environmental variable.
   
3) Test 3: Update Item Delivery Status
This test updates the delivery status of the item with trackingId set as the environmental variable.
Possible status include: PICKED_UP, IN_TRANSIT, WAREHOUSE, DELIVERED, CANCELLED
- Note: An item picked up cannot be cancelled.

4) Test 4: Item delivery status history
This test returns the delivery status history of the item with trackingId set as the environmental variable.
   
5) Test 5: Returns paginated items for delivery
This test returns the paginated list of all items registered for delivery. It is filtered by delivery status. Possible delivery status include: CREATED, PICKED_UP, IN_TRANSIT, WAREHOUSE, DELIVERED, CANCELLED
   
_**For more info/support please contact: brunookafor@gmail.com**_