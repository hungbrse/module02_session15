package module02_sesion15.ra.entity;

import module02_sesion15.ra.run.ShopManagement;

import java.util.Scanner;

public class Categories {
    private static int nextCatalogId = 1;
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;


    public Categories() {
        this.catalogId = getNextCatalogId(ShopManagement.arrCategories);
    }


    public int getCatalogId() {
        return catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    // Phương thức tự động tăng mã danh mục lớn nhất
    private int getNextCatalogId(Categories[] arrCategories) {
        int maxId = 0;
        for (Categories category : arrCategories) {
            if (category != null && category.getCatalogId() > maxId) {
                maxId = category.getCatalogId();
            }
        }
        return maxId + 1;
    }


    public void inputData(Scanner scanner, Categories[] arrCategories, int index) {
        System.out.print("Nhập tên danh mục (tối đa 50 ký tự): ");
        this.catalogName = inputCatalogName(scanner, arrCategories);

        System.out.print("Nhập mô tả danh mục: ");
        this.descriptions = scanner.nextLine();

        System.out.print("Nhập trạng thái danh mục (true: Hoạt động, false: Không hoạt động): ");
        this.catalogStatus = inputCategorieStatus(scanner);
    }

    // Kiểm tra tên danh mục không vượt quá 50 ký tự và không trùng lặp
    private String inputCatalogName(Scanner scanner, Categories[] arrCategories) {
        String name;
        do {
            name = scanner.nextLine();
            if (name.length() > 50) {
                System.err.println("Tên danh mục không được vượt quá 50 ký tự.");
            } else if (isNameDuplicate(name, arrCategories)) {
                System.err.println("Tên danh mục đã tồn tại. Vui lòng nhập lại tên khác.");
            } else {
                break;
            }
        } while (true);
        return name;
    }

    // Kiểm tra trùng lặp tên danh mục
    private boolean isNameDuplicate(String name, Categories[] arrCategories) {
        for (Categories category : arrCategories) {
            if (category != null && category.getCatalogName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Nhập trạng thái danh mục
    private boolean inputCategorieStatus(Scanner scanner) {
        do {
            String status = scanner.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Nhập trạng thái chỉ có thể là true hoặc false.");
            }
        } while (true);
    }

    // Phương thức displayData để hiển thị thông tin danh mục
    public void displayData() {
        System.out.printf("[id: %d | name: %s | description: %s | status: %s]\n", catalogId, catalogName, descriptions, catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
