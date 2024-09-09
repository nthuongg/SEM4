class Order {
  final int id;
  final String name;
  final String note;
  final int quantity;

  Order({
    required this.id,
    required this.name,
    required this.note,
    required this.quantity,

  });

  factory Order.fromJson(Map<String, dynamic> json) {
    return Order(
      id: json['id'],
      name: json['name'],
      note: json['note'],
      quantity: json['quantity'],

    );
  }
}