import 'dart:convert';
import 'dart:io';
import 'package:path/path.dart' as p;

class Subject {
  String name;
  List<int> scores;

  Subject(this.name, this.scores);

  Map<String, dynamic> toJson() {
    return{
      'name': name,
      'scores': scores,
    };
  }

  static Subject fromJson (Map<String, dynamic> json) {
    return Subject(json['name'], json['scores']);
  }

}
class Student {
  int id;
  String name;
  List<Subject> subjects;

  Student(this.id, this.name, this.subjects);

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'subjects': subjects.map((subject) => subject.toJson()).toList(),
    };
  }

  factory Student.fromJson(Map<String, dynamic> json) {
    var subjectsFromJson = json['subjects'] as List;
    List<Subject> subjectList =
          subjectsFromJson.map((i) => Subject.fromJson(i)).toList();

    return Student(
      json['id'],
      json['name'],
      subjectList,
  );
  }

  @override
  String toString() {
    return 'id: $id, name: $name, subjects: $subjects';
  }
}

void main() async {
  const String fileName= 'Student.json';
  final String directoryPath =
      p.join(Directory.current.path, 'data');
  final Directory directory =
      Directory(directoryPath);

  if(!await directory.exists()) {
    await directory.create(recursive: true);
  }
  final String filePath =
      p.join(directoryPath, fileName);
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
  if(!File(filePath).existsSync()){ //nếu file json không tồn tại
    await File(filePath).create();  // tạo file
    await File(filePath).writeAsString(jsonEncode([])); // ghi 1 mảng rỗng vào file.
    return[];
  }
//Đọc nội dung file json, chuyển đổi thành danh sách các đối tượng Student.
  String content = await File(filePath).readAsString(); //đọc nội dung file và lưu vào biến content
  List<dynamic> jsonData = jsonDecode(content);
  return jsonData.map((json) => Student.fromJson(json)).toList();
}

Future<void> addStudent(String filePath, List<Student> studentList) async {
  print('Nhập tên sinh viên: ');
  String? name = stdin.readLineSync();
  if (name == null || name.isEmpty) {
    print('Tên không hợp lệ!');
    return;
  }

  int id = studentList.isEmpty? 1:
      studentList.last.id + 1;

  List<Subject> subjects = [];
  while (true) {
    print('Nhập tên môn học: ');
    String? subjectName = stdin.readLineSync();
    if (subjectName == null || subjectName.isEmpty) {
      break;
    }

    List<int> scores = [];
    for (int i = 1; i <= 3; i++) {
      print('Nhập điểm số $i: ');
      String? scoreInput = stdin.readLineSync();
      int? score = int.tryParse(scoreInput ?? '');
      if (score == null || score < 0 || score > 10) {
        print('Điểm không hợp lệ! Nhập lại.');
        i--; // Cho phép người dùng nhập lại điểm
      } else {
        scores.add(score);
      }
    }

    subjects.add(Subject(subjectName, scores));
  }
  Student student = Student(id, name, subjects);
  studentList.add(student);
  await saveStudents(filePath, studentList);
}

Future<void> saveStudents(String filePath, List<Student> studentList) async{
  String jsonContent = jsonEncode(studentList.map((s) => s.toJson()).toList());
  await File(filePath).writeAsString(jsonContent); // ghi vao file json;
}