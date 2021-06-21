<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
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
                            <h1 class="m-0">New Product</h1>
                        </div><!-- /.col -->
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item">
                                    <button class="btn text-gray-dark" @click="submit">
                                        <i class="fas fa-check"></i>
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
                                                    <input type="file" class="custom-file-input" id="customFile" @change="handleFileChange" accept="image/png, image/jpeg">
                                                    <label class="custom-file-label" for="customFile">{{ fileName }}</label>
                                                </div>
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px">Name</span>
                                                </div>
                                                <input class="form-control" :value="product.name" @input="product.name = $event.target.value">
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px">&#8361;</span>
                                                </div>
                                                <input class="form-control" v-model="product.price">
                                            </div>

                                            <div class="input-group mb-3">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px">Category</span>
                                                </div>
                                                <select class="custom-select bg-white" aria-describedby="categoryName" v-model="product.categoryId">
                                                    <option value="1">Beverage</option>
                                                    <option value="2">Food</option>
                                                    <option value="3">Goods</option>
                                                </select>
                                            </div>

                                            <div class="input-group ">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text text-bold bg-gray-dark d-flex justify-content-center" style="width: 100px" id="productDescription">Description</span>
                                                </div>
                                                <textarea class="form-control bg-white" aria-describedby="productDescription"
                                                          :value="product.description" rows="10"
                                                          @input="product.description = $event.target.value"></textarea>
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

                                    <div class="row mb-3" style="height: 200px; overflow: auto">
                                        <table class="table table-sm table-hover text-nowrap table-valign-middle text-center">
                                            <thead>
                                            <tr class="bg-gray-dark p-3">
                                                <th style="width: 10%">ID</th>
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
                                                <td>{{ usedIngredient.ingredient.partner }}</td>
                                                <td><input class="form-control bg-white text-right" type="number" step="0.1" v-model="usedIngredient.usage"></td>
                                                <td>
                                                    <button class="btn btn-sm text-danger" @click="deleteUsedIngredient(usedIngredient)">
                                                        <i class="fa fa-trash"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="5">
                                                    <modal @insert-used-ingredient="insertUsedIngredient"></modal>
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

        Vue.component('modal', modal);

        const app = new Vue({
            el: "#content",
            data: {
                fileName: "Choose file",
                file: null,
                imageUrl: null,
                isLoading: false,
                product: {
                    name: "",
                    categoryId: 1,
                    description: "",
                    price: 0,
                    recipe: []
                }
            },
            methods: {
                submit: function (e) {
                    this.isLoading = true;

                    const formData = new FormData();

                    this.file && formData.append("image", this.file, this.fileName);
                    formData.append("product", JSON.stringify(this.product));

                    fetch("/product", {
                        method: "post",
                        body: formData
                    })
                    .then(response => response.json())
                    .then(({status, data}) => {
                        this.isLoading = false;

                        if (status === "OK") {
                            swal("Success", "The product has been successfully added.")
                                .then(() => location.href = "/product/list");
                        } else {
                            const title = data.code + " - " + data.message;
                            const content = data.details;

                            swal(title, content);
                        }
                    })
                    .catch(error => console.log(error));
                },
                handleFileChange: function (e) {
                    if (e.target.files && e.target.files[0]) {
                        this.file = e.target.files[0];
                        this.fileName = this.file.name;
                        this.imageUrl = URL.createObjectURL(this.file);
                    }
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
                            ingredient: usedIngredient,
                        }
                    ];
                },
                deleteUsedIngredient: function (usedIngredient) {
                    this.product.recipe = this.product.recipe.filter(existingIngredient => existingIngredient.ingredientId !== usedIngredient.ingredientId);
                }
            }
        })
    </script>
</body>
</html>
