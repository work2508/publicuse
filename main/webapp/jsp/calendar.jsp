<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="domain.User"%>

<!DOCTYPE html>
<html>

<head>
<title>カレンダー</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%@ include file="/jsp/google-fonts.jsp"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calander.css">

<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/main.min.css'
	rel='stylesheet' />

</head>

<body>
	
	<!-- カレンダーが表示される場所を指定するHTML要素 -->
	<div id="calendar"></div>
	
	<!-- モーダルウィンドウ（ポップアップ画面）を表示するための要素 -->
	<div id="modal-container" class="modal-container"></div>
	
	<!-- FullCalendarのライブラリ本体と多言語対応ファイルを読み込むためのタグ -->
	<script
		src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>

	<script
		src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/locales-all.global.min.js'></script>
	
	<!-- 「どこにカレンダーを描くか」と「誰の予定を取得するか」を JS に教えている -->
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
			var userId = ${user.userId};

			// 成功モーダルを生成・表示する関数
			function showSuccessModal(message) {
				const successModal = document.createElement('div');
				successModal.classList.add('success-modal-container');
				successModal.innerHTML = `
					<div class="success-modal-body">
							<p><strong class="modal-title-bold">編集成功</strong></p>
							<p>${message}</p>
						<div class="ok-button-container">
							<button class="ok-button">OK</button>
					</div>
						</div>
				`;
				document.body.appendChild(successModal);//作成したモーダル要素を、ページの<body>に追加し、画面に表示
				successModal.querySelector('.ok-button').addEventListener('click', () => {
					successModal.remove();//OKボタンがクリックされたときに、モーダル要素を画面から削除（remove()）するイベントを設定
				});
			}

			// 失敗モーダルを生成・表示する関数
			function showFailureModal(message) {
				const failureModal = document.createElement('div');
				failureModal.classList.add('failure-modal-container');
				failureModal.innerHTML = `
					<div class="failure-modal-body">
						<p><strong class="modal-title-bold">編集失敗</strong></p>
						<p>${message}</p>
						<button class="ok-button">OK</button>
					</div>
				`;
				document.body.appendChild(failureModal);
				failureModal.querySelector('.ok-button').addEventListener('click', () => {
					failureModal.remove();
				});
			}
			//FullCalendarライブラリを使って、新しくカレンダーオブジェクトを作成。基本設定
			var calendar = new FullCalendar.Calendar(calendarEl, {
				locale: 'ja',
				initialView: 'dayGridMonth',
				 height: 'auto',      
				 contentHeight: 'auto', 

				//指定された期間のイベント情報をサーバーにリクエスト送信
				//fetchInfo.endStr=リクエストに含めるクエリパラメータ
				//++囲み部分が、JavaScriptの変数とプロパティ
				events: function(fetchInfo, successCallback, failureCallback) {
					fetch('/project-test2/events?userId=' + userId + '&start=' + fetchInfo.startStr + '&end=' + fetchInfo.endStr)
						.then(res => res.json())
						.then(data => {
							successCallback(data.map(d => ({
								id: d.id,
							    title: d.exerciseName,// exerciseNameをそのままtitleとして使う
							    start: new Date(Number(d.createdAt)).toLocaleDateString('ja-JP', {
							        year: 'numeric',
							        month: '2-digit',
							        day: '2-digit'
							    }).replace(/\//g, '-'),
							    backgroundColor: d.color,//DTOのcolorをbackgroundColorにマッピング
							    borderColor: d.color// DTOのcolorをborderColorにマッピング
							})));
						})
						.catch(err => failureCallback(err));
				},
				//function(info)カレンダーの予定がクリックされたときに、その予定の情報を受け取る
				eventClick: function(info) {
					var todoId = info.event.id;
					var modalContainer = document.getElementById('modal-container');

					//1回だけリスナーを設定
					//「リスナー（listener）」とは 「ある出来事が起きたら自動的に呼び出される関数」
					modalContainer.addEventListener('click', function(event) {
					    // キャンセルボタンがクリックされたか判定
					    if (event.target.classList.contains('cancel-button-style')) {
					        modalContainer.classList.remove('active');
					    }

					    // モーダルの閉じるボタンがクリックされた場合も判定
					    if (event.target.classList.contains('modal-close') || event.target.closest('.modal-close')) {
					        modalContainer.classList.remove('active');
					    }
					});

					//指定された期間のイベント情報をサーバーにリクエスト送信
					//クリックされた予定のtodoIdとログイン中のユーザーIDを送信
					fetch('/project-test2/schedView?id=' + todoId + '&userId=' + userId)
						.then(res => res.text())//サーバーからの返答を待つ。
						.then(html => {//その返答をHTMLテキストに変換する。
							modalContainer.innerHTML = html;//変換されたHTMLを使って、モーダルの中身を書き換える。
							modalContainer.classList.add('active');//アクティブにする＝表示する

							// 閉じるボタンのイベントリスナーを設定
							modalContainer.querySelector('.modal-close').addEventListener('click', function() {
								modalContainer.classList.remove('active');
							});
							
							// 削除リンクのイベントリスナーを設定
							var deleteLink = modalContainer.querySelector('#deleteLink');
							if (deleteLink) {
								deleteLink.addEventListener('click', function(e) {
									e.preventDefault();

									fetch('/project-test2/schedDelete?id=' + todoId)
										.then(res => res.text())
										.then(deleteHtml => {
											modalContainer.innerHTML = deleteHtml;
											modalContainer.classList.add('active');

											modalContainer.querySelectorAll('.modal-close').forEach(btn => {
												btn.addEventListener('click', () => modalContainer.classList.remove('active'));
											});
										});
								});
							}

							// 編集リンクのイベントリスナーを設定
							var editLink = modalContainer.querySelector('#editLink');
		                    if (editLink) {
		                        editLink.addEventListener('click', function(e) {
		                            e.preventDefault();

		                            fetch('/project-test2/schedEdit?id=' + todoId + '&userId=' + userId)
		                                .then(res => res.text())
		                                .then(editHtml => {
		                                    modalContainer.innerHTML = editHtml;

		                                    modalContainer.querySelectorAll('.modal-close').forEach(btn => {
		                                        btn.addEventListener('click', function() {
		                                            modalContainer.classList.remove('active');
		                                        });
		                                    });
		                                    

		                                    const editForm = modalContainer.querySelector('#todo-edit-form');
		                                    const errorDiv = modalContainer.querySelector('#error-message'); 
		                                    
		                                    if (editForm) {
		                                        editForm.addEventListener('submit', function(submitEvent) {
		                                            submitEvent.preventDefault();

		                                            const formData = new FormData(editForm);
		                                            for (let pair of formData.entries()) {
		                                                if (typeof pair[1] === 'string') {
		                                                    formData.set(pair[0], pair[1].trim());
		                                                }
		                                            }

			                                          //formactionの宛先のようなもの？	
		                                            const apiUrl = "${pageContext.request.contextPath}/schedEdit";

			                                            fetch(apiUrl, {
			                                                method: "POST",
			                                                body: formData
			                                            })
		                                            	.then(response => {
		                                                // HTTPステータスが200番台以外の場合はエラー
		                                                ////JSON形式のエラーメッセージを取得し、後続のcatchブロックへ
		                                                if (!response.ok) {
		                                                    return response.json().then(data => Promise.reject(data.message));
		                                                }
		                                                // 正常な場合、JSONとしてパース
		                                                return response.json();
		                                            })
		                                            .then(data => {
		                                                // 正常なレスポンス（HTTP 200）の場合渡す
		                                                //戻り値をJSON形式で解析し、次のthenブロックへ
		                                                if (data.success) {
		                                                    showSuccessModal(data.message);
		                                                    calendar.refetchEvents();
		                                                    modalContainer.classList.remove('active');
		                                                } else {
		                                                    //datasuccessがfalseの場合失敗MSGをポップアップで表示
		                                                    showFailureModal(data.message);
		                                                }
		                                            })
		                                            .catch(error => {
		                                                // バリデーションエラーや通信エラーをここでキャッチ
		                                                console.error("エラー:", error);
		                                                // errorDivにエラーメッセージを表示
		                                                if (errorDiv) {
		                                                    errorDiv.innerHTML = error;
		                                                } else {
		                                                    showFailureModal(error);
		                                                }////errorDivが存在しない場合は、代わりに失敗モーダルを表示
		                                            });
		                                        });
		                                    }
                                        });
                                });
                            }
                        });
                }
			});

			// カレンダーのレンダリング
			calendar.render();
		});
    </script>
</body>
</html>