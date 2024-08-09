import 'dart:convert';
import 'dart:io';
import 'package:path/path.dart' as p;

class Student {
  int id;
  String name;
  String phone;


  Student(this.id, this.name, this.phone);

  Map<String, dynamic> toJson() {
    return{
      'id': id,
      'name': name,
      'phone': phone,
    };
  }
  static Student fromJson(Map<String, dynamic> json) {
    return Student(json['id'], json['name'], json['phone']);
  }

  @override
  String toString() {
    return 'ApplicationExample{id: $id, name: $name, phone: $phone}';
  }
}

void main() async{
  //DN thong tin file json
  const String fileName = 'students.json';
  final String directoryPath = p.join(Directory.current.path, 'data');
  final Directory directory = Directory(directoryPath);

  if(!await directory.exists()) {
    await directory.create(recursive: true);
  }
  final String filePath = p.join(directoryPath, fileName);
  List<Student> studentList = await loadStudents(filePath);

  while(true) {
    print('''
      Menu:
      1. Them sinh vien
      2. Hien thi thong tin sinh vien
      3. Thoat
      Moi ban chon:
    ''');

    String? choise = stdin.readLineSync();
    switch(choise) {
      case '1':
        await addStudent(filePath, studentList);
        break;

      case '2':
        await displayStudent(studentList);
        break;

      case '3':
        print('Thoat chuong trinh');
        exit(0);

      default:
        print('Vui long chon lai');
    }
  }




}
Future<List<Student>> loadStudents(String filePath) async {
  if(!File(filePath).existsSync()){
    await File(filePath).create();
    await File(filePath).writeAsString(jsonEncode([]));
    return[];
  }

  String content = await File(filePath).readAsString();
  List<dynamic> jsonData = jsonDecode(content);
  return jsonData.map((json) => Student.fromJson(json)).toList();
}

Future<void> addStudent(String filePath, List<Student> studentList) async {
  //Tao sinh vien
  //Student student = Student(1, 'Hai', '0123456789');
  print('Nhap ten sinh vien');
  String? name = stdin.readLineSync();
  if(name == null || name.isEmpty){
    print('Ten khong hop le');
    return;
  }
  print('Nhap phone sinh vien');
  String? phone = stdin.readLineSync();
  if(phone == null || phone.isEmpty) {
    print('So dien thoai khong hop le');
    return;
  }

  int id = studentList.isEmpty? 1: studentList.last.id + 1;
  Student student = Student(id, name, phone);
  //Them sinh vien
  studentList.add(student);
  //Them list vao json file
  await saveStudents(filePath, studentList);
}

Future<void> saveStudents(String filePath, List<Student> studentList) async{
  String jsonContent = jsonEncode(studentList.map((s) => s.toJson()).toList());
  await File(filePath).writeAsString(jsonContent); // ghi vao file json;
;}

Future<void> displayStudent(List<Student> studentList) async{
  if(studentList.isEmpty){
    print('Danh sach sinh vien trong');
  }else{
    print('Danh sach sinh vien');
    for(var student in studentList){
      print(student);
    }
  }
}
