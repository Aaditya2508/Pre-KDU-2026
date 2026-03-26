"use strict";

const timerDisplay = document.getElementById("timer");
const startBtn = document.getElementById("startBtn");
const pauseBtn = document.getElementById("pauseBtn");
const resetBtn = document.getElementById("resetBtn");
const setTimerBtn = document.getElementById("setTimerBtn");
const customMinutesInput = document.getElementById("customMinutes");
const sessionCountDisplay = document.getElementById("sessionCount");

let defaultMinutes = 25;
let remainingSeconds = defaultMinutes * 60;
let timerInterval = null;
let sessionsCompleted = 0;
let isRunning = false;

function formatTime(seconds) {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${String(mins).padStart(2, "0")}:${String(secs).padStart(2, "0")}`;
}

function updateDisplay() {
    timerDisplay.textContent = formatTime(remainingSeconds);
}

function clearTimerInterval() {
    if (timerInterval !== null) {
        clearInterval(timerInterval);
        timerInterval = null;
    }
    isRunning = false;
}

function startTimer() {
    if (isRunning) return;

    if (remainingSeconds <= 0) {
        remainingSeconds = defaultMinutes * 60;
    }

    isRunning = true;
    timerInterval = setInterval(() => {
        remainingSeconds--;
        updateDisplay();

        if (remainingSeconds <= 0) {
            clearTimerInterval();
            handleSessionComplete();
        }
    }, 1000);
}

function pauseTimer() {
    clearTimerInterval();
}

function resetTimer() {
    clearTimerInterval();
    remainingSeconds = defaultMinutes * 60;
    updateDisplay();
}

function handleSessionComplete() {
    alert("Session Complete!");
    sessionsCompleted++;
    sessionCountDisplay.textContent = sessionsCompleted;

    remainingSeconds = defaultMinutes * 60;
    updateDisplay();
}

/* --- CUSTOM TIMER --- */
function setCustomTimer() {
    const inputValue = customMinutesInput.value.trim();
    const minutes = Number(inputValue);

    if (
        !Number.isInteger(minutes) ||
        minutes < 1 ||
        minutes > 60
    ) {
        alert("Please enter a number between 1 and 60");
        return;
    }

    clearTimerInterval();
    defaultMinutes = minutes;
    remainingSeconds = minutes * 60;
    updateDisplay();
    customMinutesInput.value = "";
}


/* ------- EVENT LISTENERS --------- */
startBtn.addEventListener("click", startTimer);
pauseBtn.addEventListener("click", pauseTimer);
resetBtn.addEventListener("click", resetTimer);
setTimerBtn.addEventListener("click", setCustomTimer);

/* --------  INITIALIZATION ----------- */
updateDisplay();
