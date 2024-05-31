import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
import json


cred = credentials.Certificate("Your Firebase Admin SDK JSON file")
firebase_admin.initialize_app(cred, {
    'databaseURL': "Your Firebase Database URL"
})
file_path = "./extracted_data.json"
with open(file_path, 'r') as file:
    raw_data = file.read()

raw_data = raw_data.replace('\n', ' ')
raw_data = raw_data.replace('option2', 'from')
raw_data = raw_data.replace('body_html_safe', 'description')

data = json.loads(raw_data)

for product in data:
    if 'variant_title' in product:
        del product['variant_title']
for i in range(len(data)):
    data[i]['id'] = i

for product in data:
    if 'description' in product:
        product['description'] = product['description'].replace('\n', ' ')

for i in data:
    new_ref = db.reference('products').push(i)
    print('Document ID:', new_ref.key)
