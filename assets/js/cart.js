document.addEventListener('DOMContentLoaded', () => {

    // === KEY LƯU TRỮ ===
    // Đảm bảo các key này khớp với tệp script.js của bạn
    const CART_STORAGE_KEY = 'soiStuCart';
    const USER_STORAGE_KEY = 'soi_registered_user';

    // THÊM: Biến allProducts (từ tệp products.js đã nạp)
    const allProducts = (typeof products !== 'undefined') ? products : [];

    // === BIẾN TRANG GIỎ HÀNG ===
    const cartItemsContainer = document.getElementById('cart-items-list');
    const cartTotalPriceEl = document.getElementById('cart-total-price');
    const cartContainer = document.querySelector('.cart-container'); // Toàn bộ main content
    
    // Tải giỏ hàng từ localStorage
    let cart = JSON.parse(localStorage.getItem(CART_STORAGE_KEY)) || [];

    // === HÀM HỖ TRỢ ===
    const formatPrice = (price) => {
        if (isNaN(price)) { return "Giá không xác định"; }
        // Định dạng tiền Việt Nam
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
    };

    function saveCart() {
        localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(cart));
        updateCartSummary(); // Cập nhật lại tổng tiền
    }

    // === HÀM TRANG GIỎ HÀNG ===

    // Cập nhật tổng tiền
    function updateCartSummary() {
        const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
        
        if (cartTotalPriceEl) {
            cartTotalPriceEl.innerText = formatPrice(total);
        }
    }

    // Xóa một mục khỏi giỏ hàng
    function removeItemFromCart(index) {
        const item = cart[index];
        if (!item) return;

        if (confirm(`Bạn có chắc muốn xóa "${item.name} - Size ${item.size}" khỏi giỏ hàng?`)) {
            cart.splice(index, 1); // Xóa 1 mục tại vị trí index
            saveCart(); // Lưu lại giỏ hàng mới
            renderCartItems(); // Vẽ lại giỏ hàng
            
            // THÊM: Gắn lại sự kiện sau khi render
            // (Vì renderCartItems không còn tự gọi nó nữa)
            setupCartActions(); 
        }
    }
    
    // "Vẽ" các sản phẩm ra HTML
    // (THAY THẾ TOÀN BỘ HÀM NÀY)
    function renderCartItems() {
        if (!cartItemsContainer) {
            console.error("Lỗi: Không tìm thấy 'cart-items-list'.");
            return;
        }

        // 1. Xóa nội dung cũ
        cartItemsContainer.innerHTML = '';

        // 2. Kiểm tra giỏ hàng rỗng
        if (cart.length === 0) {
            cartItemsContainer.innerHTML = '<p style="text-align: center; padding: 30px; font-size: 1.1rem; color: #555;">Giỏ hàng của bạn đang trống.</p>';
            updateCartSummary(); // Cập nhật tổng tiền về 0
            return;
        }

        // 3. Lặp và tạo HTML cho từng sản phẩm
        cart.forEach((item, index) => {
            const itemPriceValue = parseFloat(item.price || 0);
            const itemQuantity = parseInt(item.quantity || 1);

            // Lấy thông tin đầy đủ của sản phẩm từ allProducts
            const productInfo = allProducts.find(p => p.id == item.id); // Dùng == để an toàn
            // Lấy size, fallback về size hiện tại nếu không tìm thấy product (dù hiếm)
            const availableSizes = productInfo ? productInfo.size : [item.size]; 

            // Tạo HTML cho các nút chọn size
            let sizeOptionsHTML = '';
            availableSizes.forEach(s => {
                sizeOptionsHTML += `<span class="edit-size-option ${s === item.size ? 'active' : ''}" data-size="${s}">${s}</span>`;
            });

            const itemHtml = `
                <div class="cart-item" data-index="${index}">
                    <!-- PHẦN XEM (MẶC ĐỊNH) -->
                    <div class="cart-item-view">
                        <div class="item-image-container">
                            <img class="item-image" src="${item.image}" alt="${item.name}">
                        </div>
                        <div class="item-details">
                            <h3 class="item-name">${item.name}</h3>
                            <p class="item-size">Size: ${item.size}</p>
                        </div>
                        <div class="item-slide-wrapper">
                            <div class="item-pricing">
                                <p class="item-price">${formatPrice(itemPriceValue)}</p> 
                                <p class="item-quantity">Số Lượng: <strong>${itemQuantity}</strong></p>
                            </div>
                        </div>
                        <div class="item-actions">
                            <button class="btn-edit"><span>Sửa</span></button>
                            <button class="btn-delete"><span>Hủy</span></button>
                        </div>
                    </div>

                    <!-- PHẦN SỬA (ẨN) -->
                    <div class="item-edit-form">
                        <div class="form-group">
                            <label>Chọn Size:</label>
                            <div class="edit-size-selector">
                                ${sizeOptionsHTML}
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Số lượng:</label>
                            <div class="edit-quantity-selector">
                                <button class="qty-btn" data-action="decrease">-</button>
                                <input type="number" class="qty-input" value="${itemQuantity}" min="1">
                                <button class="qty-btn" data-action="increase">+</button>
                            </div>
                        </div>
                        <div class="edit-actions">
                            <button class="btn-save">Lưu</button>
                            <button class="btn-cancel">Đóng</button>
                        </div>
                    </div>
                </div>
            `;
            cartItemsContainer.innerHTML += itemHtml;
        });

        // 4. Cập nhật tổng tiền
        updateCartSummary();
        
        // 5. Bỏ addEventListenersToCartItems() ở đây
        // (Sẽ gọi ở ngoài sau khi render)
    }

    // Gắn sự kiện cho các nút Xóa/Sửa
    // (THAY THẾ TOÀN BỘ HÀM addEventListenersToCartItems BẰNG HÀM NÀY)
    function setupCartActions() {
        if (!cartItemsContainer) return;

        // Xóa listener cũ (nếu có) để tránh gắn nhiều lần
        // Bằng cách thay thế node
        const newContainer = cartItemsContainer.cloneNode(true); // Sao chép
        cartItemsContainer.parentNode.replaceChild(newContainer, cartItemsContainer); // Thay thế
        
        // Cập nhật lại biến tham chiếu sau khi thay thế
        const activeCartItemsContainer = document.getElementById('cart-items-list');


        activeCartItemsContainer.addEventListener('click', (e) => {
            const cartItem = e.target.closest('.cart-item');
            if (!cartItem) return;

            // Lấy index và dữ liệu
            const index = parseInt(cartItem.dataset.index);
            if (isNaN(index)) return; // Thoát nếu không phải index
            
            const itemData = cart[index];
            if (!itemData) return;

            // --- XỬ LÝ NÚT SỬA ---
            if (e.target.closest('.btn-edit')) {
                // Đóng tất cả các item khác đang sửa
                document.querySelectorAll('.cart-item.is-editing').forEach(item => {
                    if (item !== cartItem) {
                        item.classList.remove('is-editing');
                    }
                });
                // Mở item này
                cartItem.classList.add('is-editing');
            }

            // --- XỬ LÝ NÚT ĐÓNG (TRONG FORM SỬA) ---
            if (e.target.closest('.btn-cancel')) {
                cartItem.classList.remove('is-editing');
                // Reset form về giá trị ban đầu
                const editForm = cartItem.querySelector('.item-edit-form');
                editForm.querySelector('.qty-input').value = itemData.quantity;
                editForm.querySelectorAll('.edit-size-option').forEach(opt => {
                    opt.classList.toggle('active', opt.dataset.size === itemData.size);
                });
            }

            // --- XỬ LÝ NÚT LƯU (TRONG FORM SỬA) ---
            if (e.target.closest('.btn-save')) {
                const editForm = cartItem.querySelector('.item-edit-form');
                
                const newQty = parseInt(editForm.querySelector('.qty-input').value);
                const newSizeEl = editForm.querySelector('.edit-size-option.active');
                
                if (newQty < 1) {
                    alert("Số lượng phải lớn hơn 0");
                    return;
                }
                
                if (!newSizeEl) {
                    alert("Vui lòng chọn size!");
                    return;
                }
                const newSize = newSizeEl.dataset.size;

                // Cập nhật mảng cart
                cart[index].quantity = newQty;
                cart[index].size = newSize;

                // Lưu vào localStorage
                saveCart();

                // Cập nhật giao diện (phần xem)
                const view = cartItem.querySelector('.cart-item-view');
                view.querySelector('.item-size').innerText = `Size: ${newSize}`;
                view.querySelector('.item-quantity strong').innerText = newQty;

                // Cập nhật tổng tiền (đã có trong saveCart())
                // updateCartSummary();

                // Đóng form
                cartItem.classList.remove('is-editing');
            }

            // --- XỬ LÝ NÚT HỦY (XÓA) ---
            if (e.target.closest('.btn-delete')) {
                // Hàm removeItemFromCart đã bao gồm renderCartItems và saveCart
                removeItemFromCart(index); 
            }
            
            // --- XỬ LÝ CHỌN SIZE (TRONG FORM SỬA) ---
            if (e.target.closest('.edit-size-option')) {
                const selectedSizeEl = e.target.closest('.edit-size-option');
                // Xóa active ở các nút khác
                cartItem.querySelectorAll('.edit-size-option').forEach(opt => opt.classList.remove('active'));
                // Thêm active cho nút được chọn
                selectedSizeEl.classList.add('active');
            }

            // --- XỬ LÝ +/- (TRONG FORM SỬA) ---
            if (e.target.closest('.qty-btn')) {
                const btn = e.target.closest('.qty-btn');
                const action = btn.dataset.action;
                const input = cartItem.querySelector('.qty-input');
                let currentValue = parseInt(input.value);
                
                if (action === 'increase') {
                    input.value = currentValue + 1;
                } else if (action === 'decrease' && currentValue > 1) {
                    input.value = currentValue - 1;
                }
            }
        });
    }


    // === KHỞI CHẠY ===
    
    // 1. Kiểm tra đăng nhập (Logic "gác cổng")
    if (!localStorage.getItem(USER_STORAGE_KEY)) {
        alert('Vui lòng đăng nhập để xem giỏ hàng');
        window.location.href = 'user.html';
        return; // Dừng thực thi nếu chưa đăng nhập
    }

    // 2. Nếu đã đăng nhập, hiển thị giỏ hàng
    renderCartItems();

    // 3. THAY ĐỔI: Gọi hàm setupCartActions mới
    setupCartActions(); 
});

