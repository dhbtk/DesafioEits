SELECT setval('batches_id_seq', (SELECT MAX(id) FROM batches));
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
