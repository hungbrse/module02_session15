package module02_sesion15.ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    // Constructor
    public Product() {}

    // Getter và Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    // Phương thức inputData để nhập thông tin sản phẩm
    public void inputData(Scanner scanner, Product[] arrProduct, int index, Categories[] arrCategories) {
        this.productId = inputProductId(scanner, arrProduct);
        this.productName = inputProductName(scanner, arrProduct);
        this.price = inputPrice(scanner);

        System.out.print("Nhập mô tả sản phẩm: ");
        this.description = scanner.nextLine();

        this.created = inputCreatedDate(scanner);

        // Hiển thị các danh mục sản phẩm để lựa chọn
        System.out.println("Các danh mục hiện có:");
        for (Categories cat : arrCategories) {
            if (cat != null) {
                cat.displayData();
            }
        }
        System.out.print("Chọn mã danh mục sản phẩm: ");
        this.catalogId = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống sau khi nhập int

        this.productStatus = inputProductStatus(scanner);
    }

    // Kiểm tra và nhập mã sản phẩm
    private String inputProductId(Scanner scanner, Product[] arrProduct) {
        String id;
        do {
            System.out.print("Nhập mã sản phẩm (C, S, A + 3 ký tự): ");
            id = scanner.nextLine();
            if (!id.matches("[CSA]\\d{3}")) {
                System.err.println("Mã sản phẩm không đúng định dạng. Vui lòng nhập lại.");
            } else if (isProductIdDuplicate(id, arrProduct)) {
                System.err.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
            } else {
                break;
            }
        } while (true);
        return id;
    }

    // Kiểm tra mã sản phẩm không trùng lặp
    private boolean isProductIdDuplicate(String id, Product[] arrProduct) {
        for (Product product : arrProduct) {
            if (product != null && product.getProductId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra và nhập tên sản phẩm
    private String inputProductName(Scanner scanner, Product[] arrProduct) {
        String name;
        do {
            System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
            name = scanner.nextLine();
            if (name.length() < 10 || name.length() > 50) {
                System.err.println("Tên sản phẩm phải có độ dài từ 10 đến 50 ký tự.");
            } else if (isProductNameDuplicate(name, arrProduct)) {
                System.err.println("Tên sản phẩm đã tồn tại. Vui lòng nhập lại.");
            } else {
                break;
            }
        } while (true);
        return name;
    }

    // Kiểm tra tên sản phẩm không trùng lặp
    private boolean isProductNameDuplicate(String name, Product[] arrProduct) {
        for (Product product : arrProduct) {
            if (product != null && product.getProductName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra và nhập giá sản phẩm
    private float inputPrice(Scanner scanner) {
        float price;
        do {
            System.out.print("Nhập giá sản phẩm: ");
            price = scanner.nextFloat();
            if (price <= 0) {
                System.err.println("Giá sản phẩm phải lớn hơn 0.");
            } else {
                break;
            }
        } while (true);
        scanner.nextLine(); // Đọc dòng trống sau khi nhập float
        return price;
    }

    // Kiểm tra và nhập ngày nhập sản phẩm
    private Date inputCreatedDate(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Không cho phép ngày không hợp lệ
        Date date = null;
        do {
            System.out.print("Nhập ngày tạo sản phẩm (dd/MM/yyyy): ");
            String dateString = scanner.nextLine();
            try {
                date = dateFormat.parse(dateString);
                break;
            } catch (ParseException e) {
                System.err.println("Ngày không đúng định dạng. Vui lòng nhập lại.");
            }
        } while (true);
        return date;
    }

    // Kiểm tra và nhập trạng thái sản phẩm
    private int inputProductStatus(Scanner scanner) {
        int status;
        do {
            System.out.print("Nhập trạng thái sản phẩm (0: Đang bán, 1: Hết hàng, 2: Không bán): ");
            status = scanner.nextInt();
            if (status < 0 || status > 2) {
                System.err.println("Trạng thái sản phẩm không hợp lệ. Vui lòng nhập lại.");
            } else {
                break;
            }
        } while (true);
        scanner.nextLine(); // Đọc dòng trống sau khi nhập int
        return status;
    }

    // Phương thức displayData để hiển thị thông tin sản phẩm
    public void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("[producID : %s | productName : %s | productPrice :%f |description :%s | created :s% | catalogId :%d |   productStatus : %s]\n", productId, productName,price,description,dateFormat.format(created),catalogId,    productStatus == 0  ?"đang bán " : productStatus == 1 ? "hết hàng " : "không bán ");

    }
}
