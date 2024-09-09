import 'package:flutter/material.dart';
import 'order_list_page.dart'; // Ensure these files exist
import 'add_order_page.dart';  // Ensure these files exist
import '../custom_colors.dart'; // Custom color file with defined colors

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Welcome Head Chef',
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: greenSwatch[800], // Use custom color defined in custom_colors.dart
        iconTheme: IconThemeData(color: Colors.white), // Changed to white for consistency
      ),
      drawer: Drawer(
        child: Container(
          color: greenSwatch[500], // Set the background color for the entire drawer
          child: ListView(
            padding: EdgeInsets.zero,
            children: <Widget>[
              // Custom Header to match the design
              DrawerHeader(
                decoration: BoxDecoration(
                  color: greenSwatch[800], // Matches AppBar for a unified look
                ),
                child: Row(
                  children: [
                    CircleAvatar(
                      radius: 24,
                      backgroundColor: Colors.white,
                      child: Icon(
                        Icons.person,
                        size: 30,
                        color: greenSwatch[800],
                      ),
                    ),
                    SizedBox(width: 12),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          'Head Chef',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        Text(
                          'headchef@gmail.com',
                          style: TextStyle(
                            color: Colors.white70,
                            fontSize: 14,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              ListTile(
                leading: Icon(Icons.home, color: Colors.white),
                title: Text(
                  'Home',
                  style: TextStyle(color: Colors.white),
                ),
                onTap: () {
                  Navigator.pop(context); // Close the drawer when Home is tapped
                },
              ),
              ListTile(
                leading: Icon(Icons.location_on, color: Colors.white),
                title: Text(
                  'Restaurants',
                  style: TextStyle(color: Colors.white),
                ),
                onTap: () {
                  Navigator.pop(context); // Add relevant navigation if needed
                },
              ),
              ListTile(
                leading: Icon(Icons.receipt, color: Colors.white),
                title: Text(
                  'Orders',
                  style: TextStyle(color: Colors.white),
                ),
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => OrderListPage()),
                  );
                },
              ),
              ListTile(
                leading: Icon(Icons.settings, color: Colors.white),
                title: Text(
                  'Settings',
                  style: TextStyle(color: Colors.white),
                ),
                onTap: () {
                  Navigator.pop(context); // Add relevant navigation if needed
                },
              ),
              ListTile(
                leading: Icon(Icons.info, color: Colors.white),
                title: Text(
                  'About Me',
                  style: TextStyle(color: Colors.white),
                ),
                onTap: () {
                  Navigator.pop(context); // Add relevant navigation if needed
                },
              ),
            ],
          ),
        ),
      ),
      body: Center(
        child: Text(
          'Welcome to the Restaurant App!',
          style: TextStyle(color: Colors.white, fontSize: 18),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        backgroundColor: greenSwatch[800], // Sử dụng màu theo theme
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => AddOrderPage(
                onAddOrder: (newOrder) {
                  // Xử lý sau khi thêm đơn hàng, ví dụ như cập nhật danh sách
                  print('Đơn hàng mới được thêm: ${newOrder.name}');
                },
              ),
            ),
          );
        },
        child: Icon(Icons.add),
      ),
    );
  }
}