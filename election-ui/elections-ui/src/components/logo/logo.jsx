import React from 'react';
import LogoGraphicalDefs from './logo-graphical-defs';
import LogoGraphicalPaths from './logo-graphical-paths';
import createLogoCy from './create-logo-cy';
import createLogoEn from './create-logo-en';

const svgProps = {
  'aria-hidden': 'true',
  display: 'block',
  focusable: 'false',
  height: '2em',
  preserveAspectRatio: 'xMinYMid meet'
};

const getLogo = language => {
  const createLogo = language === 'cy' ? createLogoCy : createLogoEn;

  return createLogo({ LogoGraphicalDefs, LogoGraphicalPaths });
};

const Logo = ({ language }) => {
  const LogoForLanguage = getLogo(language);

  return (
    <>
      <LogoForLanguage svgProps={svgProps} />
    </>
  );
};

export default Logo;
