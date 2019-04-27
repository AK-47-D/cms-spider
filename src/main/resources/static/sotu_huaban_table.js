// sotuSearchByTypeJson sourceType: 2
let url = 'sotuSearchByTypeJson';
let method = 'GET';

$(function () {

    let data = {page: 0, size: size, sourceType: 2};

    fetchAndRender(url, method, data);

    $('#load').on('click', () => {
            // 页码+1
            page = page + 1;
            let data = {page: page, size: size, sourceType: 2};
            fetchAndRender(url, method, data);
        }
    );
});