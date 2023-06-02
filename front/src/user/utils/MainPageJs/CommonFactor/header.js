const header = (() => {
  let bodyElement;
  let menuIconElement;
  let menuItemsElements;

  const init = () => {
    bodyElement = document.querySelector('body');
    menuIconElement = document.querySelector('.menu-icon');
    menuItemsElements = document.querySelectorAll('.nav__list-item');

    applyListeners();
  };

  const applyListeners = () => {
    menuIconElement.addEventListener('click', () => toggleClass(bodyElement, 'nav-active'));
  };

  const toggleClass = (element, className) => {
    if (element.classList.contains(className))
      element.classList.remove(className);
    else
      element.classList.add(className);
  };

  init();
})();