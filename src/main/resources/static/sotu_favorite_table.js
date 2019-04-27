// sotuSearchFavoriteJson
let url = 'sotuSearchFavoriteJson';
let method = 'GET';

$(function () {

    let data = {page: 0, size: size};

    fetchAndRender(url, method, data);

    $('#load').on('click', () => {
            // 页码+1
            page = page + 1;
            let data = {page: page, size: size};
        fetchAndRender(url, method, data);
        }
    );
});

