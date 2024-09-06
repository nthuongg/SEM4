class Product {
  int? id;
  String name;
  int price;
  String description;

  Product({this.id, required this.name, required this.price, required this.description});

  // Convert a Product into a Map. The keys must correspond to the column names in the database
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'price': price,
      'description': description,
    };
  }

  // Convert a Map into a Product
  factory Product.fromMap(Map<String, dynamic> map) {
    return Product(
      id: map['id'] as int?,  // Chuyển đổi id thành int? nếu cần
      name: map['name'] as String,
      price: map['price'] is int
          ? map['price'] as int
          : int.tryParse(map['price'].toString()) ?? 0,  // Chuyển đổi price thành int nếu nó là String
      description: map['description'] as String,
    );
  }

}
