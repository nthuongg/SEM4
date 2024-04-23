public class TestShape {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 20);
        Triangle triangle = new Triangle(10, 30);

        System.out.println("Dien tich hcn: " + rectangle.getArea());
        System.out.println("Dien tich tam giac: " + triangle.getArea());
    }
}
