// Đảm bảo rằng KEY lưu trữ thông tin người dùng phải trùng với key trong index-users.js
const USER_STORAGE_KEY = 'soi_registered_user'; 

/**
 * Hiển thị Modal thông tin người dùng và nạp dữ liệu từ localStorage
 */
function showUserInfoModal(event) {
    if (event) {
        // Ngăn chặn chuyển hướng đến #
        event.preventDefault(); 
        
        // --- CẬP NHẬT: Đóng menu bằng cách xóa inline styles ---
        const menu = document.querySelector('.user-item-menu');
        if (menu) {
            // Xóa các thuộc tính style đã được thiết lập inline
            // Điều này cho phép quy tắc CSS :hover hoạt động trở lại
            menu.removeAttribute('style'); 
        }
        // --------------------------------------------------------
    }

    const modal = document.getElementById('userInfoModal');
    const userJson = localStorage.getItem(USER_STORAGE_KEY);
    
    // Lấy thông tin người dùng từ localStorage 
    if (userJson) {
        const user = JSON.parse(userJson);

        // Nạp dữ liệu vào modal
        document.querySelector('#modalFirstName .value').textContent = user.firstName || 'N/A';
        document.querySelector('#modalLastName .value').textContent = user.lastName || 'N/A';
        document.querySelector('#modalEmail .value').textContent = user.email || 'N/A';
        document.querySelector('#modalAddress .value').textContent = user.address || 'Chưa đăng ký';
        document.querySelector('#modalPhone .value').textContent = user.phone || 'Chưa đăng ký'; 
        
        // Hiển thị modal
        if(modal) modal.classList.add('active');

    } else {
        alert("❌ Bạn chưa đăng nhập. Vui lòng đăng nhập để xem thông tin tài khoản.");
        // Chuyển hướng đến trang đăng nhập
        window.location.href = "user.html"; 
    }
}

/**
 * Ẩn Modal
 */
function hideModal() {
    const modal = document.getElementById('userInfoModal');
    if(modal) modal.classList.remove('active');
}

/**
 * Ẩn Modal khi click ra ngoài lớp phủ (nhưng không phải là modal-content)
 */
function closeModal(event) {
    // Kiểm tra xem liệu phần tử được click có phải là chính modal-overlay
    if (event.target === document.getElementById('userInfoModal')) {
        hideModal();
    }
}

/**
 * === HÀM XỬ LÝ ĐĂNG XUẤT ===
 * (Dành cho trang index.html)
 */
function handleLogout(event) {
    if (event) event.preventDefault();
    
    // Xóa thông tin người dùng khỏi localStorage
    localStorage.removeItem(USER_STORAGE_KEY);
    
    // Chuyển hướng về trang index
    window.location.href = 'index.html';
}
 
/**
 * === KHỞI CHẠY KHI TẢI TRANG ===
 * (Đây là phần quan trọng đã được cập nhật)
 */
document.addEventListener('DOMContentLoaded', () => {
    
    const userJson = localStorage.getItem(USER_STORAGE_KEY);
    const userNameDisplay = document.getElementById('userNameDisplay');
    
    // Lấy các mục menu
    const menuInfo = document.getElementById('menuInfo');
    const menuOrders = document.getElementById('menuOrders');
    const menuLogout = document.getElementById('menuLogout');
    const menuLogin = document.getElementById('menuLogin');
    const menuRegister = document.getElementById('menuRegister');
    
    // 1. KIỂM TRA ĐĂNG NHẬP VÀ CẬP NHẬT HEADER/MENU
    if (userJson && userNameDisplay) {
        // ĐÃ ĐĂNG NHẬP
        const user = JSON.parse(userJson);
        userNameDisplay.textContent = user.firstName ? user.firstName : 'Tài khoản';
        
        // Hiển thị menu đã đăng nhập
        if (menuInfo) menuInfo.style.display = 'block';
        if (menuOrders) menuOrders.style.display = 'block';
        if (menuLogout) menuLogout.style.display = 'block';
        
        // Ẩn menu chưa đăng nhập
        if (menuLogin) menuLogin.style.display = 'none';
        if (menuRegister) menuRegister.style.display = 'none';
        
    } else if (userNameDisplay) {
        // CHƯA ĐĂNG NHẬP
        userNameDisplay.textContent = 'Tài khoản';
        
        // Ẩn menu đã đăng nhập
        if (menuInfo) menuInfo.style.display = 'none';
        if (menuOrders) menuOrders.style.display = 'none';
        if (menuLogout) menuLogout.style.display = 'none';
        
        // Hiển thị menu chưa đăng nhập (đã đặt mặc định là 'block' trong HTML, nhưng an toàn hơn cứ để JS)
        if (menuLogin) menuLogin.style.display = 'block';
        if (menuRegister) menuRegister.style.display = 'block';
    }

    // 2. LOGIC KIỂM TRA GIỎ HÀNG KHI CLICK
    const cartLink = document.getElementById('cartLink');
    if (cartLink) {
        cartLink.addEventListener('click', function(event) {
            event.preventDefault(); // Ngăn chuyển trang ngay lập tức
            
            // Kiểm tra xem đã đăng nhập chưa
            const userIsLoggedIn = localStorage.getItem(USER_STORAGE_KEY);
            
            if (userIsLoggedIn) {
                // Đã đăng nhập, cho phép sang trang giỏ hàng
                window.location.href = 'cart.html';
            } else {
                // Chưa đăng nhập, thông báo và chuyển sang trang user
                alert('Vui lòng đăng nhập để xem giỏ hàng');
                window.location.href = 'user.html';
            }
        });
    }

    // 3. LOGIC CHO MENU THẢ XUỐNG (HOVER)
    const userItem = document.getElementById('userAccountItem');
    const userMenu = document.getElementById('userItemMenu');
    if (userItem && userMenu) {
        userItem.addEventListener('mouseenter', () => {
            userMenu.style.display = 'flex'; 
        });
        userItem.addEventListener('mouseleave', () => {
            userMenu.style.display = 'none';
        });

        // Xử lý khi click vào menu item (để đóng menu lại)
        userMenu.addEventListener('click', (e) => {
             if(e.target.tagName === 'A'){
                 userMenu.style.display = 'none';
             }
        });
    }
});

