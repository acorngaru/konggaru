<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
    <script src="https://unpkg.com/vuejs-paginate@latest"></script>

    <style>

        [v-cloak] {
            display: none;
        }
    </style>
<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <!-- Sidebar -->
        <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

        <!-- Header -->
        <jsp:include page="./common/header.jsp" />

        <!-- Content -->
        <div id="content" class="content-wrapper" v-cloak>
            <!-- Content Header (Page header) -->
            <div class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1 class="m-0"># {{ product.id }}</h1>
                        </div><!-- /.col -->
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item">
                                    <button class="btn text-gray-dark" @click="edit" v-if="isReadOnly">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn text-gray-dark" @click="updateProduct" v-else>
                                        <i class="fas fa-check"></i>
                                    </button>
                                </li>
                                <li class="breadcrumb-item" v-if="!isReadOnly">
                                    <button class="btn text-gray-dark" @click="cancel">
                                        <i class="fas fa-times-circle"></i>
                                    </button>
                                </li>
                                <li class="breadcrumb-item">
                                    <button class="btn text-danger" @click="deleteProduct">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </li>
                            </ol>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.container-fluid -->
            </div>
            <!-- /.content-header -->

            <!-- Main content -->
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">

                                <div v-if="isLoading" class="w-100 h-100 d-flex justify-content-center align-items-center" style="position: absolute; z-index: 123">
                                    <div class="spinner-grow text-secondary" role="status">
                                        <span class="sr-only">Loading...</span>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <div class="row">
                                        <!-- Product Image -->
                                        <div class="col-lg-4 p-2 d-flex justify-content-center">
                                            <img :src="imageUrl ? imageUrl : 'https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1-300x208.png'"
                                                 alt="preview" class="img-fluid" style="max-width: 400px"/>
                                        </div>

                                        <div class="col-lg-8 p-2">
                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px">Image</span>
                                                </div>
                                                <div class="custom-file">
                                                    <input type="file" class="custom-file-input" id="customFile" @change="handleFileChange" accept="image/png, image/jpeg" :disabled="isReadOnly">
                                                    <label class="custom-file-label" for="customFile">{{ fileName }}</label>
                                                </div>
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px"  id="productName">Name</span>
                                                </div>
                                                <input class="form-control bg-white" :disabled="isReadOnly" aria-describedby="productName" :value="product.name">
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px" id="productPrice">&#8361;</span>
                                                </div>
                                                <input class="form-control bg-white" :disabled="isReadOnly" aria-describedby="productPrice" v-model="product.price">
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px" id="categoryName">Category</span>
                                                </div>
                                                <select class="custom-select bg-white" :disabled="isReadOnly" v-model="product.categoryId" aria-describedby="categoryName">
                                                    <option value="1">Beverage</option>
                                                    <option value="2">Food</option>
                                                    <option value="3">Goods</option>
                                                </select>
                                            </div>

                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px" id="productDescription">Description</span>
                                                </div>
                                                <textarea class="form-control bg-white" aria-describedby="productDescription"
                                                          :value="product.description" rows="10"
                                                          @input="product.description = $event.target.value" :disabled="isReadOnly"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Recipe -->
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row mb-3 d-flex justify-content-between">
                                        <h3>Recipe</h3>
                                    </div>

                                    <div class="row mb-3" style="max-height: 300px; overflow: auto">
                                        <table class="table table-sm table-hover text-nowrap table-valign-middle text-center">
                                            <thead>
                                                <tr class="bg-gray-dark p-3">
                                                    <th style="width: 10%">#</th>
                                                    <th style="width: 30%">Name</th>
                                                    <th style="width: 30%">Partner</th>
                                                    <th style="width: 20%">Usage</th>
                                                    <th style="width: 10%"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr v-for="usedIngredient in product.recipe">
                                                    <td>{{ usedIngredient.ingredientId }}</td>
                                                    <td>{{ usedIngredient.ingredient.name }}</td>
                                                    <td>{{ usedIngredient.partner }}</td>
                                                    <td><input class="form-control bg-white text-right" type="number" :disabled="isReadOnly" step="0.1" v-model="usedIngredient.usage"></td>
                                                    <td>
                                                        <button class="btn btn-sm text-danger" @click="deleteUsedIngredient(usedIngredient)" :disabled="isReadOnly">
                                                            <i class="fa fa-trash"></i>
                                                        </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="5">
                                                        <modal @insert-used-ingredient="insertUsedIngredient" :is-read-only="isReadOnly"></modal>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->
                </div><!-- /.container-fluid -->
            </div>
            <!-- /.content -->
        </div>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </div>

    <jsp:include page="/WEB-INF/views/product/components/modal.jsp" />
    <script type="module">
        const product = JSON.parse('<%= request.getAttribute("product") %>');

        Vue.component('modal', modal);

        const app = new Vue({
            el: "#content",
            data: {
                product: {
                    ...product,
                    description: product.description || ""
                },
                fileName: "Choose file",
                file: null,
                imageUrl: product.imageUrl,
                isReadOnly: true,
                isLoading: false
            },
            methods: {
                handleFileChange: function (e) {
                    if (e.target.files && e.target.files[0]) {
                        this.file = e.target.files[0];
                        this.fileName = this.file.name;
                        this.imageUrl = URL.createObjectURL(this.file);
                    }
                },
                cancel: function () {
                    this.isReadOnly = true;
                    location.reload();
                },
                edit: function () {
                    this.isReadOnly = false;
                },
                updateProduct: function () {
                    this.isLoading = true;

                    const formData = new FormData();

                    this.file && formData.append("image", this.file, this.fileName);
                    formData.append("product", JSON.stringify(this.product));

                    fetch("/product/update", {
                        method: "post",
                        body: formData
                    })
                    .then(response => response.json())
                    .then(response => {
                        this.isLoading = false;
                        if (response.status === "OK") {
                            swal("Success", "Product successfully updated.", "success")
                            .then(() => {
                                location.reload();
                            });
                        } else {
                            const title = response.data.code + " - " + response.data.message;
                            const content = response.data.details;

                            swal(title, content);
                        }
                    })
                    .catch(error => console.log(error));
                },
                deleteProduct: function () {
                    swal({
                        title: "Are you sure?",
                        text: "Once deleted, you will not be able to recover this product!",
                        icon: "warning",
                        buttons: true,
                        dangerModel: true,
                    })
                    .then((willDelete) => {
                        if (willDelete) {
                            fetch("/product", {
                                headers: {
                                    "Content-Type": "application/json"
                                },
                                method: "delete",
                                body: JSON.stringify([this.product])
                            })
                            .then(response => response.json())
                            .then(response => {
                                this.isLoading = false;
                                if (response.status === "OK") {
                                    swal("Success", "Product successfully deleted.", "success")
                                        .then((() => {
                                            location.href = "/product/list";
                                        }));
                                } else {
                                    swal("Failure", "Failed to delete the product.", "error");
                                }
                            });
                        }
                    })
                },
                insertUsedIngredient: function (usedIngredient) {
                    if (this.product.recipe.find(existingIngredient => existingIngredient.ingredientId === usedIngredient.id)) {
                        swal("Failure", "Already in the product.", "error");
                        return;
                    }

                    this.product.recipe = [
                        ...this.product.recipe,
                        {
                            id: 0,
                            productId: this.product.id,
                            ingredientId: usedIngredient.id,
                            usage: 0,
                            ingredient: usedIngredient
                        }
                    ];
                },
                deleteUsedIngredient: function (usedIngredient) {
                    this.product.recipe = this.product.recipe.filter(existingIngredient => existingIngredient.ingredientId !== usedIngredient.ingredientId);
                }
            }
        });
    </script>
</body>
</html>
