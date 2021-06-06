<style>
    .page-link.active {
        color: white !important;
        background-color: #343a40;
    }
    .page-link {
        color: #343a40 !important;
    }
</style>

<script>
    const modal = Vue.component("modal", {
        template: `
            <div>
                <button class="btn" data-toggle="modal" data-target="#modal" :disabled="isReadOnly">
                    <span class="fa fa-plus-circle"></span>
                </button>

                <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="d-flex justify-content-between w-100">
                                    <h5 class="modal-title">Ingredients</h5>
                                    <div class="input-group input-group-sm" style="width: 140px">
                                        <input type="text" class="form-control" placeholder="Search here" :value="searchTerm" @input="searchIngredient">
                                        <div class="input-group-append">
                                          <button class="btn btn-dark" type="button">
                                            <i class="fa fa-search"></i>
                                          </button>
                                        </div>
                                  </div>
                                </div>

                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-body" style="height: 400px">
                                <div class="d-flex flex-column justify-content-between h-100">
                                    <table class="table table-sm table-hover text-nowrap table-valign-middle text-center" >
                                        <thead>
                                        <tr class="bg-gray-dark p-3">
                                            <th style="width: 10%">#</th>
                                            <th style="width: 30%">Name</th>
                                            <th style="width: 30%">Partner</th>
                                            <th style="width: 10%">Qauntity</th>
                                            <th style="width: 5%">Unit</th>
                                            <th style="width: 5%"></th>
                                        </tr>
                                        </thead>
                                        <tbody v-for="ingredient in page.items">
                                        <tr>
                                            <td>{{ ingredient.id }}</td>
                                            <td>{{ ingredient.name }}</td>
                                            <td>{{ ingredient.partner }}</td>
                                            <td>{{ ingredient.quantity }}</td>
                                            <td>{{ ingredient.unit }}</td>
                                            <td>
                                                <button class="btn btn-sm text-gray-dark" @click="insertUsedIngredient(ingredient)">
                                                    <i class="fa fa-plus-circle"></i>
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <nav>
                                        <ul class="pagination justify-content-end">
                                            <li :class="['page-item', {disabled: !page.hasPrevPage}]" aria-label="Previous">
                                                <button class="page-link" @click="findIngredients(page.currentPageNo - 1)">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </button>
                                            </li>
                                            <li v-for="pageNo in range">
                                                <button :class="['page-link', {'active': page.currentPageNo === pageNo}]" @click="findIngredients(pageNo)">{{ pageNo }}</button>
                                            </li>
                                            <li :class="['page-item', {disabled: !page.hasNextPage}]" aria-label="Next">
                                                <button class="page-link" @click="findIngredients(page.currentPageNo + 1)">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </button>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `,
        props: {
            isReadOnly: Boolean
        },
        data: function () {
            return {
                ingredients: [],
                searchTerm: "",
                page: {
                    rows: 7,
                    pageCount: 3,
                    currentPageNo: 1,
                    startPage: 0,
                    endPage: 0,
                    totalItems: 0,
                    totalPages: 0,
                    hasNextPage: false,
                    hasPrevPage: false,
                    items: null
                }
            }
        },
        computed: {
            range: function () {
                const pages = [];

                for (let i = this.page.startPage; i <= this.page.endPage; i++)
                    pages.push(i);

                return pages;
            }
        },
        methods: {
            findIngredients: function (pageNo) {
                fetch("/ingredient/list?pageNo=" + pageNo + "&rows=" + this.page.rows + "&searchTerm=" + this.searchTerm, {
                    method: "post"
                }).then(response => response.json())
                  .then(page => this.page = page);
            },
            searchIngredient: function (e) {
                this.searchTerm = e.target.value;
                this.findIngredients(1);
            },
            insertUsedIngredient: function (ingredient) {
                this.$emit('insert-used-ingredient', ingredient);
            }
        },
        created () {
            this.findIngredients(1);
        }
    });
</script>
