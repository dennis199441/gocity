import axios from 'axios';

export const isLogin = () => {
    return true;
}

export const formatDate = (date) => {
    if(!date) {
        return null
    }
    date = new Date(date);
    let year = date.getFullYear();
    let month = (1 + date.getMonth()).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');
    let hh = date.getHours().toString().padStart(2, '0');;
    let mm = date.getMinutes().toString().padStart(2, '0');;
    return month + "/" + day + "/" + year + " " + hh + ":" + mm;
}

export const httpGet = async (url) => {
    try {
        let response = await axios({
            method: 'get',
            url: url,
        });

        if (response.status === 200) {
            return response.data;
        }
    } catch (e) {
        console.error(e);
    }

    return null;
}

export const httpPost = async (url, data) => {
    try {
        let response = await axios({
            method: 'post',
            url: url,
            data: data
        });

        if (response.status === 200) {
            return response.data;
        }
    } catch (e) {
        console.error(e);
    }

    return null;
}

export const httpPut = async (url, data) => {
    try {
        let response = await axios({
            method: 'put',
            url: url,
            data: data
        });

        if (response.status === 200) {
            return response.data;
        }
    } catch (e) {
        console.error(e);
    }

    return null;
}

export const httpDelete = async (url, data) => {
    try {
        let response = await axios({
            method: 'delete',
            url: url,
            data: data
        });

        if (response.status === 200) {
            return response.data;
        }
    } catch (e) {
        console.error(e);
    }

    return null;
}