function home() {
    window.location.href = "/index.jsp";
}

function redirectToLogin() {
    window.location.href = "/login.jsp";
}

function mypage() {
    window.location.href = "/mypage";
}

function myinfo() {
    window.location.href = "/main.jsp";
}

function deldata(id) {
    if (confirm("정말 탈퇴하시겠습니까?")) {
        fetch(`/mypage?id=${encodeURIComponent(id)}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`서버 응답 오류: ${text}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                alert(data.message);
                window.location.href = '/index.jsp';
            })
            .catch(error => {
                alert('오류가 발생했습니다: ' + error.message);
            });
    }
}


document.addEventListener("click", function (event) {
    const searchHistoryDiv = document.getElementById("search-history");
    if (!event.target.closest(".search-history")) {
        searchHistoryDiv.style.display = "block"; // 검색창 클릭 시 드롭다운 리스트 보이기
    }
});


function showSearchHistory() {
    var searchHistoryContainer = document.getElementById("search-history");
    var historycontainer = document.getElementById("history-items");

    searchHistoryContainer.innerHTML = "";  // 초기화
    historycontainer.innerHTML = "";  // 검색 기록 초기화

    if (searchHistoryData.length > 0) {
        searchHistoryContainer.style.display = "block";
        historycontainer.style.display = "block";

        var current_text = document.createElement("div");
        current_text.id = "current_text";
        current_text.innerText = "최근 검색어";
        searchHistoryContainer.appendChild(current_text); // 자식 요소로 넣기

        // 검색 기록을 div로 추가
        searchHistoryData.forEach(function (history, index) {
            var historyItem = document.createElement("div");
            historyItem.classList.add("history-item"); // css 스타일 지정

            // 검색어와 날짜를 묶을 div
            var historyText = document.createElement("div");
            historyText.classList.add("history-text");
            historyText.innerHTML = `<strong>${history.search}</strong><br><small>${history.search_date}</small>`;

            // 각 삭제 버튼
            var deleteBtn = document.createElement("button");
            deleteBtn.classList.add("delete-btn");
            deleteBtn.innerText = "X";
            deleteBtn.onclick = function () {
                searchHistoryData.splice(index, 1);  // 해당 검색 기록 삭제
                deleteSearchHistoryBySearchText(history.user_id,history.search);
                showSearchHistory();  // 삭제 후 다시 기록 표시
            };

            historyItem.appendChild(historyText);  // 검색어와 날짜 추가
            historyItem.appendChild(deleteBtn);    // 삭제 버튼 추가
            historycontainer.appendChild(historyItem);  // historycontainer에 기록 추가
        });

        searchHistoryContainer.appendChild(historycontainer);

        var btn_del = document.createElement("button");
        btn_del.type = "button";
        btn_del.id = "btn_del";
        btn_del.innerText = "기록 삭제";
        btn_del.onclick = function () {
            searchHistoryData = [];  // 모든 기록 삭제
            deleteSearchHistory(history.user_id);
            showSearchHistory();  // 삭제 후 다시 표시
        };
        searchHistoryContainer.appendChild(btn_del);

    } else {
        var noHistoryItem = document.createElement("div");
        noHistoryItem.id = "no_history_item";
        noHistoryItem.innerText = "최근 검색기록 내역이 없습니다.";
        searchHistoryContainer.appendChild(noHistoryItem);
    }
}

function showdata() {
    // 서버의 URL
    const url = '/search_history';

    // GET 요청 보내기
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // JSON 응답을 받기
        })
        .then(data => {
            console.log('Success:', data);
            searchHistoryData = data; // searchHistoryData 업데이트
            showSearchHistory(); // 검색 기록 표시
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


document.addEventListener("click", function (event) {
    var searchHistoryContainer = document.getElementById("search-history");
    if (!event.target.closest(".search-container")) {
        searchHistoryContainer.style.display = "none";  // 검색창 외부 클릭 시 드롭다운 숨기기
    }
});

function deleteSearchHistory(id) {
    fetch(`/search_history?id=${encodeURIComponent(id)}`, { method: 'DELETE' })
        .then(response => response.json())
        .then(data => {
            if (data) {
                console.log('Success');
            } else {
                console.log('Fail');
            }
        })
        .catch(error => {
            console.error('삭제 중 오류 발생:', error);
        });
}

function deleteSearchHistoryBySearchText(id, search) {
    const url = `/search_history?id=${encodeURIComponent(id)}&search_text=${encodeURIComponent(search)}`;

    fetch(url, { method: 'DELETE' })
        .then(response => response.json())
        .then(data => {
            if (data) {
                console.log('Success');
            } else {
                console.log('Fail');
            }
        })
        .catch(error => {
            console.error('삭제 중 오류 발생:', error);
        });
}
function CreatePost() {
    window.location.href = "/webapp/WEB-INF/views/create_post.jsp";
}

function one() {
    document.getElementById('price').addEventListener('input', function() {
        let value = this.value;
        if (!value.startsWith('₩')) {
            value = '₩ ' + value.replace(/[^0-9]/g, ''); // 원화 기호와 숫자만 유지
        } else {
            value = '₩ ' + value.replace(/[^0-9]/g, ''); // 원화 기호와 숫자만 유지
        }
        this.value = value;
    });
}

function CheckLengthTitle() {
    var input = document.getElementById("title");
    if(input.value.length > 20){
        input.value = input.value.substring(0,20); // 20자 이상이면 입력 X
    }
}

function CheckLengthContent() {
    var input = document.getElementById("content");
    if(input.value.length > 100){
        input.value = input.value.substring(0,100); // 100자 이상이면 입력 X
    }
}

