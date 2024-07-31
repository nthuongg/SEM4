import 'dart:io';

class Student{
  int id;
  String name;
  String phoneNumber;

  Student(this.id, this.name, this.phoneNumber);

  @override
  String toString() {
    return 'ID: $id, Name: $name, Phone Number: $phoneNumber';
  }
}
void main(){
  //Ham chinh goi cac ham khac
  List<Student> students = [];
  while(true){
    print('''
    Menu:
    1. Thêm sinh viên 
    2. Hiển thị danh sách sinh viên
    3. Tìm kiếm sinh viên
    4. Sửa thông tin sinh viên
    5. Xóa sinh viên
    6. Thoát 
        ''');
    String? choice = stdin.readLineSync();
    switch(choice){
      case '1':addStudent(students);break;
      case '2':displayStudent(students);break;
      case '3':findStudent(students);break;
      case '4':updateStudent(students);break;
      case '5':deleteStudent(students);break;
      case '6':print('Thoát chương trình');exit(0);
      default:print('Chọn sai. Vui lòng chọn lại.');
    }
  }
}
void addStudent(List<Student> students){
  print('Nhập ID sinh viên: ');
  int? id = int.tryParse(stdin.readLineSync() ?? '');
  if(id == null){
    print('ID không hợp lê');
    return;
  }
  print('Nhập tên sinh viên');
  String? name = stdin.readLineSync();
  if(name ==null ||name.isEmpty){
    print('Tên không hợp lê');
    return;
  }

  print('Nhập số điện thoại');
  String? phoneNumber = stdin.readLineSync();
  if(phoneNumber == null || phoneNumber.isEmpty){
    print('Số điện thoại không hợp lệ');
    return;
  }

  students.add(Student(id, name, phoneNumber));
  print('Sinh viên đã được thêm.');
}

Student? findStudent(List<Student> students, [bool displayResult = true]) {
  print('Nhập ID cần tìm: ');
  int? id = int.tryParse(stdin.readLineSync() ?? '');

  if (id == null) {
    print('ID không hợp lệ.');
    return null;
  }

  Student? student = students.firstWhere((s) => s.id == id, orElse: () => Student(0, '', ''));
  if (student.id == 0) {

      print('Không tìm thấy sinh viên với ID này');

    return null;
  } else {
    if (displayResult) {
      print('Thông tin sinh viên: $student');
    }
    return student;
  }
}

void updateStudent(List<Student> students) {
  Student? student = findStudent(students, false);
  if (student == null) {
    return;
  }

  print('Nhập tên mới: ');
  String? name = stdin.readLineSync();
  if (name != null && name.isNotEmpty) {
    student.name = name;
  }

  print('Nhập số điện thoại mới: ');
  String? phone = stdin.readLineSync();
  if (phone != null && phone.isNotEmpty) {
    student.phoneNumber = phone;
  }

  print('Thông tin sinh viên đã được cập nhật.');
}

void deleteStudent(List<Student> students) {
  Student? student = findStudent(students, false);
  if (student == null) {
    return;
  }

  students.remove(student);
  print('Đã xóa sinh viên.');

}




void displayStudent(List<Student> students){
  if(students.isEmpty){
    print('Danh sách sinh viên trống.');
  }else{
    print('Danh sách sinh viên: ');
    for(var student in students){
      print(student);
    }
  }
}