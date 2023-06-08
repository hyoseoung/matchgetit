import React, { useEffect } from 'react';
import '../../styles/MatchingPage/matchingPage.css';
import FriendlyMatch from './FriedlyMatch';
import LeagueMatch from './LeagueMatch';

const Match = () => {
  useEffect(() => {
    const handleBlueIconEvent = () => {
      const subPage1 = document.querySelector('.subPage1');
      const subPage2 = document.querySelector('.subPage2');
      subPage1.classList.add('active');
      subPage1.classList.remove('inactive');
      subPage2.classList.remove('active');
      subPage2.classList.add('inactive');
    };

    const handleRedIconEvent = () => {
      const subPage1 = document.querySelector('.subPage1');
      const subPage2 = document.querySelector('.subPage2');
      subPage2.classList.add('active');
      subPage2.classList.remove('inactive');
      subPage1.classList.remove('active');
      subPage1.classList.add('inactive');
    };

    const redIcon = document.querySelector('.redIcon');
    const blueIcon = document.querySelector('.blueIcon');
    redIcon.addEventListener('click', handleRedIconEvent);
    redIcon.addEventListener('touchstart', handleRedIconEvent);
    blueIcon.addEventListener('click', handleBlueIconEvent);
    blueIcon.addEventListener('touchstart', handleBlueIconEvent);

    return () => {
      redIcon.removeEventListener('click', handleRedIconEvent);
      redIcon.removeEventListener('touchstart', handleRedIconEvent);
      blueIcon.removeEventListener('click', handleBlueIconEvent);
      blueIcon.removeEventListener('touchstart', handleBlueIconEvent);
    };
  }, []);

  return (
    <>
      <img className="ballIcon blueIcon" src={process.env.PUBLIC_URL + '/images/blueballIcon.svg'} alt="redballIcon" />
      <img className="ballIcon redIcon" src={process.env.PUBLIC_URL + '/images/redballIcon.svg'} alt="redballIcon" />
      <div className="subPage subPage1 active">
        <div className="subcontainer">
          <FriendlyMatch />
        </div>
      </div>

      <div className="subPage subPage2 inactive">
        <div className="subcontainer">
          <LeagueMatch />
        </div>
      </div>

      <div className="gatherPeoplePage" style={{ display: 'none' }}></div>
      <div className="waitingAcceptPage" style={{ display: 'none' }}></div>
    </>
  );
}

export default Match;