# 🐾 寵物用品管理系統（Pet Shop Management System）

本專案為以 Java Swing 製作的桌面應用程式，結合 MySQL 資料庫，提供店家或管理員進行寵物用品之商品管理、訂單查詢與庫存監控等功能。專案以 GUI 介面操作，適合初學者參考完整的 CRUD + JDBC 整合應用。

## 🔧 使用技術

- Java（JDK 8+）
- Java Swing（GUI）
- MySQL 資料庫
- JDBC 連線操作
- MVC 架構設計
- Git / GitHub 版本控管

## 📁 專案結構

pet-shop-management-system/
├── src/ // Java 原始碼
│ ├── model/ // 資料處理與 DAO
│ ├── view/ // Swing GUI 介面
│ ├── controller/ // 使用者操作控制
│ └── Main.java // 程式進入點
├── db.properties // 資料庫連線設定（已加入 .gitignore，不上傳）
├── init.sql // 初始化資料表與範例資料
└── .gitignore


## 💡 主要功能

- 商品查詢（可依名稱、分類、價格範圍）
- 訂單紀錄查詢（顧客端）
- 所有訂單總覽、熱門商品查詢（管理員端）
- 低庫存商品提示（管理員端）
- Swing 圖形化介面操作
- MySQL 資料庫查詢與更新（JDBC）

## 🧪 如何使用

1. 下載專案至本機並開啟 IDE（建議使用 VS Code 或 Eclipse）
2. 匯入 MySQL JDBC 驅動（需放入 lib 資料夾或加到 classpath）
3. 建立資料庫並執行 `init.sql` 初始化資料表與測試資料
4. 設定 `db.properties`：

   ```properties
   db.url=jdbc:mysql://localhost:3306/petdb
   db.user=root
   db.password=你的密碼
執行 Main.java 即可開啟 GUI 系統介面

🔐 注意事項
db.properties 為私密設定，請勿上傳至 GitHub（已透過 .gitignore 排除）
本專案為個人學習與練習用途，尚未進行安全與例外處理最佳化，請勿直接應用於正式商業環境

📷 預覽畫面

<img width="600" alt="截圖 2025-06-30 下午11 36 58" src="https://github.com/user-attachments/assets/1a72d00b-c35c-4edc-8581-b342124a0ca7"/></br>
<img width="600" alt="截圖 2025-06-30 下午11 14 29" src="https://github.com/user-attachments/assets/28226a37-899f-4cc6-864d-aa091b58bc40"/></br>
<img width="600" alt="截圖 2025-06-30 下午11 15 01" src="https://github.com/user-attachments/assets/93ab0df1-04e1-495d-b5f2-59a72a039422"/></br>

## 📊 系統資料庫設計

本系統共包含數個主要資料表，用以記錄商品、顧客、訂單與訂單明細，透過下方 ER Model 可快速理解資料表關聯。

🧩 ER Model
![ERModel](https://github.com/user-attachments/assets/fa2d75b4-e11c-482d-8f43-e7ab784f2fa2)

🗂️ 關聯綱目表
![schema-diagram](https://github.com/user-attachments/assets/3b8abc90-3abc-45cf-a326-a4598e5a876a)

🙋‍♀️ 作者介紹

由軟體工程系學生開發，期末專題為班上最高分。正在轉職為全端工程師的道路上努力中 💪
歡迎參觀我的其他作品：GitHub(https://github.com/sunnnnnus/)
