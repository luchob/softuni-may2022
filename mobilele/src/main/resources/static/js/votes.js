const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

function showVotes(offerId) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/api/votes/${offerId}`, requestOptions)
        .then(response => response.json())
        .then(json => {
            document.getElementById("upvotes").textContent = json.positive
            document.getElementById("downvotes").textContent = json.negative
        })
        .catch(error => console.log('error', error));
}

function voteUp(event) {

    const offerId = event.currentTarget.dataset.offerid;
    const vote = {
        positive: 1,
    };

    doVote(offerId, vote)
}

function voteDown(event) {
    const offerId = event.currentTarget.dataset.offerid;

    const vote = {
        negative: 1,
    };
    doVote(offerId, vote)
}

async function doVote(offerId, vote) {

    const requestBody = JSON.stringify(vote);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfHeaderValue,
            "Content-Type" : "application/json",
            "Accept" :"application/json"
        },
        body: requestBody
    }

    const response = await fetch(`http://localhost:8080/api/votes/${offerId}`, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
}

